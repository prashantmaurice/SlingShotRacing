package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.maurice.GameWorld.GameWorld;
import com.sun.org.apache.bcel.internal.generic.LUSHR;

public class Car {
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	public static int HEIGHT=20;
	public static int WIDTH=20;
	private boolean isAlive=true;
	private GameWorld world;
	private int waypoint=1;
	private int speed=5;
	Array<Vector2> path;
	private Level level;
	private float tolerance=5;//tolerence from car to vertex
	private float angleCar=0;
	private float distCar=30;
	public Car(float x, float y, int velX, int velY, GameWorld gameWorld) {
		world=gameWorld;
		path=world.getLevel().getPath();
		level=world.getLevel();
		//world.getLevel()
		position = new Vector2(x, y);
		velocity = new Vector2(velX, 0);
		acceleration = new Vector2(0, 0.5f);
		setAlive(true);
		
	}
	public void update(){
		if(isWaypointReached()){
			System.out.println("Direction changed");
			System.out.println("lavel pos="+path.get(waypoint));
			level.translate(new Vector2(path.get(waypoint).x-getX(),path.get(waypoint).y-getY()));
			System.out.println("lavel pos="+path.get(waypoint));
			//position.x=path.get(waypoint).x;
			//position.y=path.get(waypoint).y;
			waypoint++;
			if(waypoint==path.size)
				waypoint=0;
		}
		System.out.println("waypoint="+path.get(waypoint));
		System.out.println("position="+position);
		float angle=(float) Math.atan2(path.get(waypoint).y-getY(), path.get(waypoint).x-getX());
		//System.out.println("angle="+angle);
		velocity.set((float) Math.cos(angle)*speed,(float) Math.sin(angle)*speed);
		//System.out.println("velocity="+velocity);
		
		//velocity.y=vel;
			
			//velocity.x+=acceleration.x;
			//velocity.y+=acceleration.y;
			//position.x+=velocity.x;
			//position.y+=velocity.y;
	}
	public boolean isWaypointReached(){
		return ((Math.abs(path.get(waypoint).x-getX()) <=tolerance)&&
				(Math.abs(path.get(waypoint).y-getY()) <=tolerance));
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public Vector2 getPosition(){
		return position;
	}
	public Vector2 getVelocity(){
		return velocity;
	}
	public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }
    public float getAngleCar(){
    	return angleCar;
    }
    public float getDistCar(){
    	return distCar;
    }
}
