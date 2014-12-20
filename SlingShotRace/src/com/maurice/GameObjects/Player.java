package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameWorld;
import com.sun.org.apache.bcel.internal.generic.LUSHR;

public class Player {
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	public static int HEIGHT=20;
	public static int WIDTH=20;
	private boolean isAlive=true;
	private boolean inAir=true;
	private GameWorld world;
	
	private int NEXTJUMP_MARGIN=10;
	
	private int GROUND_HEIGHT;
	private int surfaceHeight;
	public int jumpNum=0;
	public Player(float x, float y, int velX, int velY, GameWorld gameWorld) {
		world=gameWorld;
		GROUND_HEIGHT=world.getGroundHeight();
		position = new Vector2(x, y);
		velocity = new Vector2(velX, velY);
		acceleration = new Vector2(0, 0.5f);
		setAlive(true);
	}
	public void update(){
		surfaceHeight=world.getTopSurfaceHeight();
		if((position.y+velocity.y)<surfaceHeight){			
			velocity.x+=acceleration.x;
			velocity.y+=acceleration.y;
			position.x+=velocity.x;
			position.y+=velocity.y;
			inAir=true;;
		}
		else{
			position.y=surfaceHeight;
			jumpNum=0;
			inAir=false;
		}
		if((position.y+NEXTJUMP_MARGIN)<surfaceHeight){
			inAir=true;;
		}
		else{
			inAir=false;
		}
		System.out.println("jump="+jumpNum);
		
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
	public void jump() {
		if((!inAir)|(jumpNum==1)){			
			jumpNum++;
			velocity.y=-10;
		}
	}
	public boolean isInAir() {
		return inAir;
	}
	
}
