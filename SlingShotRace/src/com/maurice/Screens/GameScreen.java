package com.maurice.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.maurice.GameWorld.GameRenderer;
import com.maurice.GameWorld.GameWorld;
import com.maurice.GameHelpers.MyInputGestureListener;
import com.maurice.slingshotRace.SlingShotRaceGame;

public class GameScreen implements Screen{

	SlingShotRaceGame game;
    private GameWorld world;
    private GameRenderer renderer;
    private int score=123;//useful for final display of score after gameover
    //private int level=1;//level played at start
    //private Rectangle rect;
    // This is the constructor, not the class declaration
    public GameScreen(SlingShotRaceGame slingShotRaceGame) {
        System.out.println("GameScreen Attached");
        this.game = slingShotRaceGame;
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		//float gameWidth = screenWidth;
		//float gameHeight = screenHeight;
		float gameWidth = 300;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

		int midPointX = (int) (gameWidth / 2);
		int midPointY = (int) (gameHeight / 2);
        
		System.out.println("Screenmidpoint==="+midPointX+"=="+midPointY);
        world = new GameWorld(midPointX,midPointY,(int)gameHeight, (int)gameWidth,this.game, this);
        renderer = new GameRenderer(world,midPointX, midPointY, (int)gameHeight, (int)gameWidth);
        
        Gdx.input.setInputProcessor(new GestureDetector(new MyInputGestureListener(world, this)));
    }
    

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render(world.getRuntime());
        //game.yourScore=(int) world.getRuntime();
        //game.setYourScore(124);
    }
    public SlingShotRaceGame getGame(){
    	return this.game;
    }
    public int getHeight(){
    	return Gdx.graphics.getHeight();
    }
    public int getWidth(){
    	return Gdx.graphics.getWidth();
    }
    public int getScore() {
    	//return 10;
    	return score;
    }
    public void setScore(int score) {
    	//world.setScore(score);
    }
    @Override
    public void resize(int width, int height) {
        System.out.println("GameScreen - resizing");
        renderer.resize(width, height);
    }

    @Override
    public void show() {
        System.out.println("GameScreen - show called");
    }

    @Override
    public void hide() {
        System.out.println("GameScreen - hide called");     
    }

    @Override
    public void pause() {
        System.out.println("GameScreen - pause called");        
    }

    @Override
    public void resume() {
        System.out.println("GameScreen - resume called");       
    }

    @Override
    public void dispose() {
        this.dispose();
    }

}
