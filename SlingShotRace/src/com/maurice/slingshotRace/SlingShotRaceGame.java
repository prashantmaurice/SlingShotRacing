package com.maurice.slingshotRace;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.maurice.GameHelpers.AssetLoader;
import com.maurice.Screens.FinishedScreen;
import com.maurice.Screens.GameScreen;
import com.maurice.Screens.MainMenuScreen;
import com.maurice.Screens.RestartScreen;

public class SlingShotRaceGame extends Game {
    public GameScreen gameScreen;
    
	@Override
	public void create() {
		System.out.println("Game Created!");
		AssetLoader.load();
        gameScreen = new GameScreen(this);
        setScreen(gameScreen);
	}
	@Override
	public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
	public void setGameScreen(){
		
		setScreen(gameScreen);
	}
	public void setNewGameScreen(){
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
	public void quitGame(){		
		//Gdx.app.exit();
	}
}
