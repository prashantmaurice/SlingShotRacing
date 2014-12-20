package com.maurice.GameObjects;

import java.util.ArrayList;
import java.util.Random;

import com.maurice.GameWorld.GameWorld;

public class ScrollHandler {
	
	private ArrayList<Scrollable> blocks= new ArrayList<Scrollable>();
    // Capital letters are used by convention when naming constants.
    public static final int SCROLL_SPEED = -400;
    public static final int GROUND_HEIGHT = 200;
    Random rand = new Random();
    private int lastBlock=0;
    private Level level;

	public ScrollHandler(float xPos,float yPos,GameWorld gameWorld) {
    	//blocks.add(new Block(800,GROUND_HEIGHT,10,10,SCROLL_SPEED));
    	//blocks.add(new Block(500,GROUND_HEIGHT,10,10,SCROLL_SPEED));
    	//blocks.add(new Block(1000,GROUND_HEIGHT-50,10,10,SCROLL_SPEED));
		level=new Level(0, 0, 0, 0, gameWorld);
    	
    	
    }
    public void update(float delta) {
        // Update our objects
    	level.update(delta);
    	
    	
    	
    	
    	/*
    	lastBlock++;
    	//System.out.println("LastBlock="+lastBlock);
    	if(lastBlock>30){    		
    		if(rand.nextInt(7)==1){
    			lastBlock=0;
    			blocks.add(new Block(600,GROUND_HEIGHT-(rand.nextInt(5)*50),10,10,SCROLL_SPEED));//random blocks generation
    		}
    		else if(rand.nextInt(5)==1){
    			blocks.add(new Coin(600,GROUND_HEIGHT-(rand.nextInt(5)*50),10,10,SCROLL_SPEED));//random blocks generation
    		}
    		else if(rand.nextInt(5)==1){
    			blocks.add(new Shield(600,GROUND_HEIGHT-(rand.nextInt(5)*50),10,10,SCROLL_SPEED));//random blocks generation
    		}
    	}
    	for (int i = 0; i < blocks.size(); i++) {
			Scrollable c = (Scrollable) blocks.get(i);	
			c.update(delta);
			if (c.isScrolledLeft()) {
	            //c.reset(c.getTailX() + 600+rand.nextInt(200));
				blocks.remove(i);
			}
		}
    	bg1.update(delta);
    	bg2.update(delta);
    	if(bg1.isScrolledLeft()){
    		bg1.reset(bg1.getTailX()+600);
    	}
    	else if(bg2.isScrolledLeft()){
    		bg2.reset(bg2.getTailX()+600);
    	}*/
    }

	public ArrayList<Scrollable> getBlocks() {
		return blocks;
	}
	public Level getLevel(){
		return level;
	}

}
