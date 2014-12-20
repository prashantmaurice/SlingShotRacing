package com.maurice.GameHelpers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameObjects.Pin;
import com.maurice.GameWorld.GameWorld;
import com.maurice.Screens.GameScreen;

public class MyInputGestureListener implements GestureListener  {
	GameWorld world;
	int gameWidth;
	private int touchMargin=10;
    public MyInputGestureListener(GameWorld world, GameScreen gameScreen ) {
    	this.world=world;
    	gameWidth=gameScreen.getWidth();
    	//this.pinRadius=world.getPinRadius();
    }
    
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		System.out.println("Swiped=="+velocityX);
		return false;
	}
	
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		/*if (world.isReady()) {
            world.start();
            return false;
        }
		else if (world.isRunning()) {
			world.jump();
            /*if((gameWidth/2)<x){
            	world.jump();
            }
            else{
            	world.roll();
            }
            return false;
        }
		else if (world.isGameover()) {
            world.restart();
            return false;
        }*/
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}


	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		System.out.println("PanStopped...!");
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
