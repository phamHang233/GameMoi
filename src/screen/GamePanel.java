package screen;

import java.awt.*;  //import graphics class
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entity.MonsterGraphic;
import entity.NPC_OldMan;
import entity.OldMan_Graphic;
import entity.Player;
import entity.PlayerGraphic;
import entity.Princess_Graphic;
import event.EventHandler;
import main.Main;
import main.Sound;
import move.CollisionChecker;
import move.KeyHandler;
import object.Projectile;
import object.SuperObject;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable { // GamePanel is a subclass of JPanel

    // SCREEN SETTINGS

    public final int tileSize = 48; // 48x48
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12; // => 4x3 map
    public final int screenWidth = tileSize * maxScreenCol; //960 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576 pixels
    
    // FOR FULL SCREEN SIZE
    private int screenWidth2 = screenWidth;
    private int screenHeight2 = screenHeight;
 	private BufferedImage tempScreen;
 	Graphics2D g2;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeigth = tileSize * maxWorldRow;
    public final int maxMap =10 ;
    public int currentMap = 0 ;

    //FPS
    private final  int FPS = 60;

    //SETTINGS
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public Sound music = new Sound();
    public Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public boolean fullScreenOn = false;
    public Config config = new Config(this);
    public EventHandler eHandler = new EventHandler(this);
    public UI ui = new UI(this);
    private Thread gameThread; //  a thread starts until it is stopped, need to implements Runnable interface
    
    //ENTITY AND OBJECT
    public PlayerGraphic playerGra = new PlayerGraphic(this, keyH);   
    public SuperObject obj[][] = new SuperObject[maxMap][20]; // can display up to 10 objects at the same time
    public OldMan_Graphic[][] oldMan_Gra= new OldMan_Graphic[maxMap][10] ;
    public Princess_Graphic princess_Gra[][] = new Princess_Graphic[maxMap][10] ;
    public MonsterGraphic monsterGra[][] = new MonsterGraphic[maxMap][10];
    public ArrayList<Projectile> projectileList = new ArrayList<>();
    
    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int setNameState = 1;
    public final int playState = 2;
    public final int pauseState = 3;
    public final int dialogueState = 4;
    public final int characterState = 5; 
    public final int optionsState = 6;
    public final int gameOverState = 7; 

    public GamePanel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // improving rendering performance
        this.addKeyListener(keyH); // so GamePanel can recognize key input
        this.setFocusable(true);// GamePanel can be "focused" to receive key input
    }

    public void setupGame() {
        aSetter.setObject();
    	aSetter.setNPC();
    	aSetter.setMonster();
        gameState = titleState;
        
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
    	g2 = (Graphics2D)tempScreen.getGraphics();
        if(fullScreenOn == true) {
    		setFullScreen();
    	}
    }
    public void retry() {
    	playerGra.setDefaultPositions();
    	playerGra.player.restoreLifeAndMana(playerGra);
    	aSetter.setNPC();
        aSetter.setMonster();
    }
    public void restart() {
    	playerGra.setDefaultPositions();
    	playerGra.player.restoreLifeAndMana(playerGra);
    	playerGra.setItems();
    	aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
//        aSetter.setInteractiveTile();
    	
    }
    public void setFullScreen() {
    	//GET LOCAL SCREEN DEVICE
    	GraphicsEnvironment ge =  GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gd = ge.getDefaultScreenDevice();
    	gd.setFullScreenWindow(Main.window);

    	//GET FULL SCREEN HEIGHT AND WIDTH
    	screenWidth2 = Main.window.getWidth();
    	screenHeight2 = Main.window.getHeight();
    }

    public void startGameThread() {

        gameThread = new Thread(this); // this - this class - GamePanel class
        gameThread.start(); // automatically call run function
    }
  
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval; // check if delta == drawInterval, currentTime - lastTime = pasttime
            timer += currentTime - lastTime;
            lastTime = currentTime;
            
            if(delta >= 1) {
                update();
                //repaint();
                drawToTempScreen(); // draw everything to the buffered image
                drawToScreen();		// draw the buffered image to the screen
                delta--;
            
            }
            if (timer >= 1000000000) {
            	timer = 0;
            }
        }
    }
    public void update() {
    	if(gameState == playState) {
    		//PLAYER
    		playerGra.update();
    		//NPC
    		for(int i =0; i < oldMan_Gra.length; i++) {
    			if(oldMan_Gra[currentMap][i] != null) {
                    oldMan_Gra[currentMap][i].update(oldMan_Gra[currentMap][i], oldMan_Gra[currentMap][i].npc);
    			}
    		}

            for(int i =0; i < princess_Gra.length; i++) {
    			if(princess_Gra[currentMap][i] != null) {
                    princess_Gra[currentMap][i].update(princess_Gra[currentMap][i], princess_Gra[currentMap][i].npcPrint);
    			}
    		}
    		
   		    //MONSTER
    		 for(int i =0; i < monsterGra.length; i++) {
            	 if(monsterGra[currentMap][i] != null) {
            			if (monsterGra[currentMap][i].isAlive() == true && monsterGra[currentMap][i].isDying() == false) {
            				monsterGra[currentMap][i].update(monsterGra[currentMap][i] ,monsterGra[currentMap][i].monGreen);
            			}
            			if(monsterGra[currentMap][i].isAlive() == false) {
            				monsterGra[currentMap][i].checkDrop();
            				monsterGra[currentMap][i] = null;
            			}
            	}

            }
          // PROJECTILE
             for(int i = 0; i < projectileList.size(); i++) {
                 if(projectileList.get(i) != null) {
                        if (projectileList.get(i). isAlive() == true) {
                             projectileList.get(i).update();
                        }
                        if(projectileList.get(i). isAlive() == false) {
                             projectileList.remove(i);
                        }
                 }

            }
    	}
    	if(gameState == pauseState) {
    		//nothing
    	}
    }
    public void drawToTempScreen() {
    	if(gameState == titleState){
        	ui.draw(g2);
        }
        else {
            tileM.draw(g2); 
            //OBJECT
            for(int i = 0; i < obj.length; i++) {
                if(obj[currentMap][i] != null) {
                    obj[currentMap][i].draw(g2,this);
                }
            }
            //NPC
            for(int i = 0; i < oldMan_Gra.length; i++) {
            	if(oldMan_Gra[currentMap][i] != null) {
            		oldMan_Gra[currentMap][i].draw(g2, oldMan_Gra[currentMap][i], oldMan_Gra[currentMap][i].npc);
            	}
            }
            for(int i = 0; i < princess_Gra.length; i++) {
            	if(princess_Gra[currentMap][i] != null) {
            		princess_Gra[currentMap][i].draw(g2, princess_Gra[currentMap][i], princess_Gra[currentMap][i].npcPrint);
            	}
            }
            
            //MONSTER
            for(int i = 0; i < monsterGra.length; i++) {
            	if(monsterGra[currentMap][i] != null) {
            		monsterGra[currentMap][i].draw(g2, monsterGra[currentMap][i], monsterGra[currentMap][i].monGreen);
            	}
            }
            for(int i = 0; i < projectileList.size(); i++) {
                if(projectileList.get(i) != null) {
                		projectileList.get(i).draw(g2);
                }
            }
            
            //Player
            playerGra.draw(g2);
            //UI 
            ui.draw(g2); 
        }
    }
    public void drawToScreen() {
    	Graphics g = getGraphics();
    	g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
    	g.dispose();
    }
    
    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {

        se.setFile(i);
        se.play();
    }
   
}
