package com.maurice.GameWorld;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.maurice.GameHelpers.AssetLoader;
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

public class GameRenderer {
    
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    
    private int midPointX;
    private int midPointY;
    private int gameHeight;
    private int gameWidth;
    //colors-light
    private Color color1=colorFromHex(0xE60000L);//0xrrggbb//red
	private Color color2=colorFromHex(0x0099FFL);//blue
	private Color color3=colorFromHex(0xF28080L);//light red
	private Color color4=colorFromHex(0x80CCFFL);//light blue
	private Color[] colors = new Color[]{color1,color2,color3,color4};
	//colors-faded
	private Color color1d=colorFromHex(0xA10000L);//0xaarrggbb
	private Color color2d=colorFromHex(0xB26B00L);
	private Color color3d=colorFromHex(0x005C99L);
	private Color[] colorsfaded = new Color[]{color1d,color3d,color2d};
	
	private SpriteBatch batch, batchTime;
	Texture texture;
	Texture rock;
	private Animation sonicAnimation;
	int margin=2;
	Sprite sprite;
	private TextureRegion region;
	private TextureRegion bg, grass, coin;

    private static final int VIRTUAL_WIDTH = 600;
    private static final int VIRTUAL_HEIGHT = 300;
    private static final float ASPECT_RATIO =(float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;
    private int GROUND_HEIGHT;//FROM TOP
    
    private Rectangle viewport;
    private SpriteBatch sb;
    
    private Player player;
    private ArrayList<Scrollable> blocks;
    private ScrollHandler scroller;
    Level level;
    Array<Vector2> path;
    Car car;
    
    public GameRenderer(GameWorld world, int midPointX,int midPointY,int gameHeight,int gameWidth) {
        myWorld = world;
        car=world.getCar();
        
        this.midPointX=midPointX;
        this.midPointY=midPointY;
        this.gameHeight=gameHeight;
        this.gameWidth=gameWidth;
        
        sb = new SpriteBatch();
        cam = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        cam.setToOrtho(true, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        
        batch = new SpriteBatch();  //text display
        batch.setProjectionMatrix(cam.combined);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
       
        initGameObjects();
        initAssets();
    }
    private void initGameObjects() {
    	/*GROUND_HEIGHT=myWorld.getGroundHeight();
        player=myWorld.getPlayer();
        scroller = myWorld.getScroller();
        blocks = scroller.getBlocks();
        background1=scroller.getBg1();
        background2=scroller.getBg2();
        */
    }
    private void initAssets() {
        /*bg = AssetLoader.bg;
        grass = AssetLoader.grass;
        sonicAnimation = AssetLoader.sonicAnimation;
        coin = AssetLoader.coin;
        /*birdMid = AssetLoader.bird;
        birdDown = AssetLoader.birdDown;
        birdUp = AssetLoader.birdUp;
        skullUp = AssetLoader.skullUp;
        skullDown = AssetLoader.skullDown;
        bar = AssetLoader.bar;
        */
    }

    public void render(float runtime) {
    	// update camera
        cam.update();
        cam.apply(Gdx.gl10);
        // set viewport to 
        Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);

        // clear previous frame
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        /*
        // DRAW EVERYTHING
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);	
        shapeRenderer.rect(0,0, 600, 300);//game virtual size
        shapeRenderer.end();
        
        //DRAW BACKGROUND
        batch.begin();
        //batch.draw(bg, 0, 0, 600, 100);
        batch.draw(AssetLoader.bg,background1.getX(), 100, 600, 100);
        batch.draw(AssetLoader.bg,background2.getX(), 100, 600, 100);
        batch.end();*/
        
        //DRAW BACKGROUND GROUND
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);	
        shapeRenderer.rect(0,0, 600, 300);
        shapeRenderer.end();
        
        //DRAW LEVEL
        Level level=myWorld.getLevel();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.CYAN);	
        shapeRenderer.rect(level.getX(),level.getY(), Level.WIDTH, Level.HEIGHT);
        //shapeRenderer.line(v0, v1)
        shapeRenderer.end();
        
        //DRAW PATH
        shapeRenderer.begin(ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);	
        //shapeRenderer.rect(level.getX(),level.getY(), Level.WIDTH, Level.HEIGHT);
        //shapeRenderer.line(v0, v1)
        path=level.getPath();
        Vector2 temp1,temp2;
        for(int i=0;i<path.size;i++){
			temp1=path.get(i);
			if(i==path.size-1)
				temp2=path.get(0);
			else
				temp2=path.get(i+1);
			shapeRenderer.line(temp1, temp2);
		}
        shapeRenderer.end();
        
        //DRAW CAR BASE
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.YELLOW);	
        shapeRenderer.rect(300,150, Car.WIDTH, Car.WIDTH);
        shapeRenderer.end();
        
        //DRAW
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.YELLOW);	
        shapeRenderer.rect(300,150, Car.WIDTH, Car.WIDTH);
        shapeRenderer.end();
        
        
        /*
        //DRAW PLAYER
        batch.begin();
        batch.enableBlending();
        if(myWorld.isSheild){
        	batch.draw(AssetLoader.cover,
	        		player.getPosition().x-10,player.getPosition().y-30, 40, 40);
        }
        shapeRenderer.setColor(Color.RED);
        //shapeRenderer.rect(player.getPosition().x,player.getPosition().y-Player.HEIGHT, Player.WIDTH, Player.HEIGHT);
        if(player.isInAir())
        	batch.draw(AssetLoader.sonicAnimation.getKeyFrame(1),
        	        		player.getPosition().x,player.getPosition().y-Player.HEIGHT, Player.WIDTH, Player.HEIGHT);
        else
        	batch.draw(AssetLoader.sonicAnimation.getKeyFrame(runtime),
        //batch.draw(AssetLoader.sonic1,
        		player.getPosition().x,player.getPosition().y-Player.HEIGHT, Player.WIDTH, Player.HEIGHT);
        batch.end();
        //System.out.println("t6player at="+player.getPosition().x+"="+player.getPosition().y);
        
        //DRAW BLOCKS AND COINS
        shapeRenderer.begin(ShapeType.Filled);
        int x=600;
    	for (int i = 0; i < blocks.size(); i++) {
    		Scrollable c = (Scrollable) blocks.get(i);
			if(c.getClass()==Block.class){				
				shapeRenderer.setColor(Color.BLACK);	
				shapeRenderer.rect(c.getX(), c.getY()-Block.HEIGHT,Block.WIDTH,Block.HEIGHT);
				shapeRenderer.rect(x-=12, 10,10,10);
			}
			if(Coin.class==c.getClass()){
				batch.begin();
				batch.draw(AssetLoader.coin,
		                c.getX(), c.getY()-Coin.HEIGHT, Coin.WIDTH, Coin.HEIGHT);
				batch.end();
			}
			if(Shield.class==c.getClass()){
				batch.begin();
				batch.draw(AssetLoader.shield,
		                c.getX(), c.getY()-Coin.HEIGHT, Coin.WIDTH, Coin.HEIGHT);
				batch.end();
			}
		}
    	//System.out.println("Blocks number="+blocks.size());
    	shapeRenderer.end();
    	
    	//DRAW SCORE
    	batch.begin();
    	AssetLoader.font.setColor(Color.GRAY);
        AssetLoader.font.setScale((float) 0.5F);
        AssetLoader.font.draw(batch, "SCORE="+myWorld.getScore(), 10, 10);
        batch.end();
        
        //DRAW TOAST
    	batch.begin();
        AssetLoader.font.setScale((float) 1);
        AssetLoader.font.draw(batch, myWorld.getToast(), 270, 10);
        batch.end();
        
        //DRAW UP BUTTON
        batch.begin();
		batch.draw(AssetLoader.upButton,500, 210, 80, 80);
		batch.end();
		
		//DRAW LOGO
        batch.begin();
        AssetLoader.font.setColor(Color.ORANGE);
        AssetLoader.font.setScale((float) 2);
        AssetLoader.font.draw(batch, "DASH", 50, 230);
        AssetLoader.font.setColor(Color.GRAY);
        AssetLoader.font.setScale((float) 0.5F);
        AssetLoader.font.draw(batch, "DEVELOPED BY MAURICE", 50, 280);
		batch.end();
        
    	//DRAW GAMEOVER
    	if(myWorld.isGameover()){
            batch.begin();
            AssetLoader.font.setColor(Color.ORANGE);
            AssetLoader.font.setScale((float) 2);
            AssetLoader.font.draw(batch, "GAME OVER", 200, 50);
            AssetLoader.font.setColor(Color.GRAY);
            AssetLoader.font.setScale((float) 0.8f);
            AssetLoader.font.draw(batch, "TRY AGAIN ?", 330, 100);
            batch.end();
    	}
    	if(myWorld.isReady()){
            batch.begin();
            AssetLoader.font.setColor(Color.ORANGE);
            AssetLoader.font.setScale((float) 2);
            AssetLoader.font.draw(batch, "START ?", 200, 50);
            AssetLoader.font.setColor(Color.BLACK);
            AssetLoader.font.setScale((float) 0.7F);
            AssetLoader.font.draw(batch, "HOW TO PLAY", 200, 100);
            AssetLoader.font.setScale((float) 0.5F);
            AssetLoader.font.draw(batch, "> TAP TO JUMP", 200, 140);
            AssetLoader.font.draw(batch, "> DOUBLE TAP TO JUMP MID AIR", 200, 160);
            batch.end();
    	}
        //draw time
    	//batchTime.begin();
        //font.draw(batchTime, timeString((int)(myWorld.getRuntime()*60)), 600, 300);
        //batchTime.end();
         * */
         
    }
    private String timeString(int time){
    	String temp="";
 		int temp2=0;
 		//temp2=(int)time/3600;
 		//temp+=((temp2<10)?"0":"")+temp2+":";//hours
 		temp2=(int)time/60;
 		temp+=((temp2<10)?"0":"")+temp2+":";//minutes
 		temp2=(int)time%60;
 		temp+=((temp2<10)?"0":"")+temp2;//seconds
 		//temp2=(int)(time*100)%60;
 		//temp+=((temp2<10)?"0":"")+temp2;//milliseconds
 		return temp;
     }

	private Color colorFromHex(long hex){
            float a = (hex & 0xFF000000L) >> 24;
            float r = (hex & 0xFF0000L) >> 16;
            float g = (hex & 0xFF00L) >> 8;
            float b = (hex & 0xFFL);   
            return new Color(r/255f, g/255f, b/255f, a/255f);
    }
    public void resize(int width, int height) { 
    	// set aspect rations and padding accordingly
        float aspectRatio = (float)width/(float)height;
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        if(aspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)VIRTUAL_HEIGHT;
            crop.x = (width - VIRTUAL_WIDTH*scale)/2f;
        }
        else if(aspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
            crop.y = (height - VIRTUAL_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)VIRTUAL_WIDTH;
        }

        float w = (float)VIRTUAL_WIDTH*scale;
        float h = (float)VIRTUAL_HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
    }
}