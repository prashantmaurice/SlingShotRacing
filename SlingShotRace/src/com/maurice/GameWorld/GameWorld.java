package com.maurice.GameWorld;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameObjects.Ball;
import com.maurice.GameObjects.Car;
import com.maurice.GameObjects.Cluster;
import com.maurice.GameObjects.ConnectLine;
import com.maurice.GameObjects.Level;
import com.maurice.GameObjects.Pin;
import com.maurice.GameObjects.Player;
import com.maurice.GameObjects.ScrollHandler;
import com.maurice.GameObjects.Scrollable;
import com.maurice.GameObjects.Tile;
import com.maurice.GameObjects.Wheel;
import com.maurice.Screens.GameScreen;
import com.maurice.slingshotRace.SlingShotRaceGame;
import com.sun.xml.internal.ws.api.pipe.NextAction;


public class GameWorld{
	
	private int midPointX;
    private int midPointY;
    private int gameHeight;
    private int gameWidth;
    public static int PLAYER_POSX=100;
    public int score=0;
    
    public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	private GameState currentState;
    public enum GameState {
        READY, RUNNING, GAMEOVER
    }
    private SlingShotRaceGame game;
    private GameScreen gameScreen;
    private float runtime=0;
    private static final int GROUND_HEIGHT = 200;//FROM TOP
    private int gameSpeed=5;//set speed
    private ArrayList<Scrollable> blocks;
    private int lastBlock=0;
    public boolean isSheild=false;

	Random rand = new Random();
	private Car car;
	private ScrollHandler scroller;
	String toast="";
	int lastToast=0;
	public GameWorld(int midPointX,int midPointY, int gameHeight, int gameWidth, SlingShotRaceGame game2, GameScreen gameScreen) {
		currentState=GameState.READY;
		this.midPointX=midPointX;
		this.midPointY=midPointY;
		this.gameHeight=gameHeight;
		this.gameWidth=gameWidth;
		this.gameScreen=gameScreen;
		
		System.out.println("GameWidth="+gameWidth);
		System.out.println("GameHeight="+gameHeight);
		//this.tileWidth=gcd(gameHeight,gameWidth);
		this.game=game2;
		scroller = new ScrollHandler(gameWidth,gameHeight,this);
		car=new Car(scroller.getLevel().getPath().get(0).x,
				scroller.getLevel().getPath().get(0).y,0,0,this);
		
		//blocks=scroller.getBlocks();
		//addBlockNow();
		currentState=GameState.RUNNING;//enable this to auto start
		
	}
	public void update(float delta) {
		switch (currentState) {
        case READY:
        	updateReady(delta);
            break;
        case RUNNING:
        	updateRunning(delta);
        	break;
        case GAMEOVER:
        	updateGameover(delta);
        	break;
        default:
            break;
        }
	}
	public void updateReady(float delta){
		
	}
	public void updateRunning(float delta){
		runtime+=delta;
    	car.update();
    	scroller.update(delta);
    	if(--lastToast<=0){
    		lastToast=0;
    		toast="";
    	}
    	/*updateAllBlocks();
    	if(lastBlock++>10){			
    		if(rand.nextInt(100)==1) {
    			addBlockNow();//change thi to increase blocks frequency
    			lastBlock=0;
    		}
    	}*/
    	checkCollision();
    	
	}
	public void updateGameover(float delta){
	}
	public  SlingShotRaceGame getGame(){
		return this.game;
	}
	public float getRuntime() {
		return runtime;
	}
	public void setRuntime(float runtime) {
		this.runtime = runtime;
	}
	public Car getCar() {
		return car;
	}
	public int getGroundHeight() {
		/*//blocks=scroller.getBlocks();
		int temp=GROUND_HEIGHT;
		for (int i = 0; i < blocks.size(); i++) {
			Block c = (Block) blocks.get(i);
			if(((c.getX()-Player.WIDTH)<PLAYER_POSX)&((PLAYER_POSX-c.getX())<Block.WIDTH)){
				temp= Math.min((int)c.getX(), temp);
			}
		}*/
		//return temp;
		return GROUND_HEIGHT;
	}
	public int getTopSurfaceHeight() {
		//blocks=scroller.getBlocks();
		/*int temp=GROUND_HEIGHT;
		for (int i = 0; i < blocks.size(); i++) {
			Block c = (Block) blocks.get(i);
			if(((c.getX()-Player.WIDTH)<PLAYER_POSX)&((PLAYER_POSX-c.getX())<Block.WIDTH)){
				temp= Math.min((int)c.getX(), temp);
			}
		}
		return temp;*/
		return GROUND_HEIGHT;
	}
	public int getGameSpeed() {
		return gameSpeed;
	}
	private void addBlockNow(){
		//blocks.add(new Block(600,GROUND_HEIGHT,this));
	}
	public ArrayList<Scrollable> getBlocks() {
		return blocks;
	}
	public void checkCollision(){
		//blocks=scroller.getBlocks();
		//System.out.println("Collision blocks="+blocks.size());
		/*
		for (int i = 0; i < blocks.size(); i++) {
			Scrollable c = (Scrollable) blocks.get(i);
			if(c.getX()-Player.WIDTH>PLAYER_POSX)
				break;//not checking all blocks
			if(((c.getX()-Player.WIDTH)<PLAYER_POSX)&((PLAYER_POSX-c.getX())<Block.WIDTH)){
				if((c.getY()<(player.getPosition().y+Block.HEIGHT))&((player.getPosition().y-c.getY())<Player.HEIGHT)){
					//System.out.println("Collided...at ="+i+"th block");
					if(c.getClass()==Coin.class){
						score+=10;
						blocks.remove(i);
						toast="+10";
						lastToast=30;
						continue;
					}
					if(c.getClass()==Shield.class){
						score+=30;
						blocks.remove(i);
						isSheild=true;
						toast="Shield acquired";
						lastToast=30;
						continue;
					}
					if(isSheild){
						score+=20;
						blocks.remove(i);
						isSheild=false;
						toast="Shield saved your ass";
						lastToast=30;
						continue;
					}
					currentState=GameState.GAMEOVER;
				}
			}
		}*/
	}
	public boolean isReady(){
		return currentState==GameState.READY;
	}
	public boolean isRunning(){
		return currentState==GameState.RUNNING;
	}
	public boolean isGameover(){
		return currentState==GameState.GAMEOVER;
	}
	public void start(){
		currentState=GameState.RUNNING;
	}
	public void restart(){
		score=0;
		blocks.clear();
		//blocks.add(new Block(300,GROUND_HEIGHT,10,10,scroller.SCROLL_SPEED));
    	//blocks.add(new Block(600,GROUND_HEIGHT,10,10,scroller.SCROLL_SPEED));
		currentState=GameState.RUNNING;
	}
	public ScrollHandler getScroller() {
        return scroller;
    }
	public String getToast(){
		return toast;
	}
	public Level getLevel(){
		return scroller.getLevel();
	}
	

}
