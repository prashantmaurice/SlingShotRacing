package com.maurice.GameHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.maurice.GameObjects.Player;

public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg, grass;

    public static Animation sonicAnimation;
    public static TextureRegion sonic1, sonic2, sonic3, sonic4;
    public static TextureRegion coin, upButton, cover, shield;

    public static TextureRegion skullUp, skullDown, bar;
    
    public static BitmapFont font, shadow;
    private static int playerWidth=Player.WIDTH;
    private static int playerHeight=Player.HEIGHT;

    public static void load() {
    	
    	//LOAD FONT
    	font = new BitmapFont(Gdx.files.internal("data/devgothic.fnt"),true);//true means solves upside-down text problem
        font.setScale(2);
        font.setColor(Color.GRAY);
    	
    	
        //texture.setEnforcePotImages(false);
        //texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        //bg = new TextureRegion(texture, 0, 0, 136, 43);
        //bg.flip(false, true);
        /*
        //LOAD BACKGROUND
        texture.setEnforcePotImages(false);
        texture = new Texture(Gdx.files.internal("data/bg2.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        bg = new TextureRegion(texture, 0, 0, 600, 100);
        bg.flip(false, true);
        
        //grass = new TextureRegion(texture, 0, 43, 143, 11);
        //grass.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/sonic1xs.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        sonic1 = new TextureRegion(texture, 0, 0, playerWidth, playerHeight);
        sonic1.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/sonic2xs.png"));
        sonic2 = new TextureRegion(texture, 0, 0, playerWidth, playerHeight);
        sonic2.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/sonic3xs.png"));
        sonic3 = new TextureRegion(texture, 0, 0, playerWidth, playerHeight);
        sonic3.flip(false, true);
        texture = new Texture(Gdx.files.internal("data/sonic4xs.png"));
        sonic4 = new TextureRegion(texture, 0, 0, playerWidth, playerHeight);
        sonic4.flip(false, true);
        
        TextureRegion[] sonics = { sonic1, sonic2, sonic3, sonic4 };
        sonicAnimation = new Animation(0.06f, sonics);
        sonicAnimation.setPlayMode(Animation.LOOP);
        
        //LOAD COIN
        texture = new Texture(Gdx.files.internal("data/coin.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        coin = new TextureRegion(texture, 0, 0, Coin.WIDTH, Coin.HEIGHT);
        coin.flip(false, true);
        
        //LOAD UPBUTTON
        texture = new Texture(Gdx.files.internal("data/upbutton.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        upButton = new TextureRegion(texture, 0, 0, 80, 80);
        upButton.flip(false, true);
        
        //LOAD CHARACTER SHIELD
        texture = new Texture(Gdx.files.internal("data/shieldcover.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        cover = new TextureRegion(texture, 0, 0, 40, 40);
        cover.flip(false, true);
        
      //LOAD CHARACTER SHIELD
        texture = new Texture(Gdx.files.internal("data/shield.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        shield = new TextureRegion(texture, 0, 0, 20, 20);
        shield.flip(false, true);
        /*
        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);

        TextureRegion[] birds = { birdDown, bird, birdUp };
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);
    	 */
    }
    
    public static void dispose() {
        // We must dispose of the texture when we are finished.
        //texture.dispose();
    	font.dispose();
    }

}
