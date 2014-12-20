package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.maurice.GameWorld.GameWorld;
import com.sun.org.apache.bcel.internal.generic.LUSHR;

public class Level extends Scrollable{
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	public static int HEIGHT=1000;
	public static int WIDTH=1000;
	private GameWorld world;
	Array<Vector2> path, pathOriginal;
	Vector2 origin;
	Vector2 start;
	Vector2 midPoint=new Vector2(300,150);
	
	public Level(float x, float y, int velX, int velY, GameWorld gameWorld) {
		super(x, y, WIDTH, HEIGHT, 0, 0);
		path=new Array<Vector2>();
		pathOriginal=new Array<Vector2>();
		loadMap();
		start=path.get(0).cpy();
		start.sub(midPoint.cpy());
		origin=new Vector2(x,y);
		setPosition(start);//set initial posiion to 0 th point
		world=gameWorld;
		//position = new Vector2(x, y);
		//velocity = new Vector2(velX, velY);
		//acceleration = new Vector2(0, 0.5f);
	}
	public void update(float delta) {
		translate(world.getCar().getVelocity());
        //position.add(velocity.cpy().scl(delta));
        System.out.println("level update=");
        // If the Scrollable object is no longer visible:
        //if (position.x + width < 0) {
          //  isScrolledLeft = true;
        //}
    }
	public void loadMap(){
		path.add(new Vector2(400, 200));//start
		path.add(new Vector2(200, 200));
		path.add(new Vector2(200, 600));
		path.add(new Vector2(600, 600));
		path.add(new Vector2(600, 200));
		for(int i=0;i<path.size;i++){
			pathOriginal.add(path.get(i).cpy());
		}
	}
	public void translate(Vector2 move){
		origin.sub(move.cpy());
		for(int i=0;i<path.size;i++){
			path.get(i).sub(move.cpy());
			//System.out.println("path"+i+"="+path.get(i).x+"="+path.get(i).y);
		}
	}
	public void setPosition(Vector2 move){
		origin.sub(move.cpy());
		for(int i=0;i<path.size;i++){
			path.get(i).sub(move.cpy());
			//System.out.println("path"+i+"="+path.get(i).x+"="+path.get(i).y);
		}
	}
	public Array<Vector2> getPath(){
		return path;
	}
}
