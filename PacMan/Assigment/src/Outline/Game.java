package Outline;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.List;

import Object.Dots;
import Object.Fruits;
import Object.Ghosts;
import Object.PacMan;
import Object.PowerPellets;
import Object.Walls;
import Screen.GamemapScreen;
import Screen.Window;
import keyboardControl.PlayerListener;
/**
 *  @author Han HouZhi 18206155
 * This includes creating and updating the game,
 * this updates the information of the game like scores,game level and so on
 */


public class Game implements Runnable { 
	/**
	 * this has threads to do many operations,
	 *  updating ,checking collision,moving
	 */
	private static final Rectangle SCREEN_BOUNDS = new Rectangle(0, 0, Window.SCREEN_WIDTH, Window.SCREEN_HEIGHT);

	private final int TARGET_FPS = 30;
	private final double GAME_HERTZ = 30.0;
	private final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
	private final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
	private final int MAX_UPDATES_BEFORE_RENDER = 5;
	
	private double getpowertime;
	private PacMan pacman;
	private Ghosts ghosts;
	private Dots dots;
	private Fruits fruits;
	private PowerPellets power;
	private Walls walls;
	private Level[] level;
	private int currentLevel = 0;
	private String filename1=System.getProperty("user.dir")/*得到程序的根目录*/+"/src/level1.txt";
	private String filename2=System.getProperty("user.dir")/*得到程序的根目录*/+"/src/level2.txt";
	private GamemapScreen gameScreen;
	private PlayerListener listener;
	private boolean pause = false;
	private int playerLives;
	private int score;
	/**
	 * this method is used to check if the game is paused
	 * @return return true if the game is paused,otherwise false
	 */
	public boolean isPaused() {
		return pause;
	}
	/**
	 * this method is used to paint the game on the screen.
	 * @param listener this is the keyboard listener that control the pacman
	 * @param g paint bush
	 */
	public Game(PlayerListener listener, GamemapScreen g) {
		this.gameScreen = g;
		g.addGame(this);
		this.listener = listener;
		playerLives = 3;
		score = 0;
		level = new Level[2];
		level[0] = new Level(5,filename1);
		level[1] = new Level(8,filename2);
		walls = new Walls(level[currentLevel].getstring());
		pacman = new PacMan(listener,walls.getRect());
		ghosts = new Ghosts(level[currentLevel].getstring(),walls.getRect(),level[currentLevel].getSpeed());
		dots = new Dots(level[currentLevel].getstring());
		fruits = new Fruits(level[currentLevel].getstring());
		power = new PowerPellets((level[currentLevel].getstring()));
	}
	/**
	 * this method is used to get the shapes of walls,
	 * @return return the shapes of walls
	 */
	public List<Shape> getWalls() {
		return walls.getShapes();
	}
	/**
	 * this method is used to get the shapes of dots,
	 * @return return the shapes of dots
	 */
	public List<Shape> getDots() {
		return dots.getShapes();
	}
	/**
	 * this method is used to get the shapes of Fruits,
	 * @return return the shapes of Fruits
	 */
	public List<Shape> getFruits() {
		return fruits.getShapes();
	}
	/**
	 * this method is used to get the shapes of PowerPellets,
	 * @return return the shapes of PowerPellets
	 */
	public List<Shape> getPowerPellets() {
		return power.getShapes();
	}
	/**
	 * get the screen bounds
	 * @return screen bounds
	 */
	public static Rectangle getScreenBounds() {
		return new Rectangle(SCREEN_BOUNDS);
	}
	/**
	 * this method let pacman start to move on the screen
	 */
	private void movePacman() {
		pacman.move();
	}
	/**
	 * this method let Ghosts start to move on the screen
	 */
	private void moveGhosts() {	
		ghosts.moveAll();
	}
	/**
	 * this method checks the collisions between pacman and ghosts
	 * @param pm this is pacman
	 * @param gh these are ghosts
	 * @return return true if they collided,otherwise false
	 */
	private boolean checkcollisions(Rectangle pm,List<Rectangle> gh){
    	boolean iscollisions = false;
    	for (Rectangle s : gh) {
		    if(pm.intersects(s)) {
		    	iscollisions = true;
		    }
	    }
    	return iscollisions;
    }
	/**
	 * After pacman ate power pellet, this method let pacman be able to eat ghost
	 * (in a short time)
	 * @return return true if ghost can be ate ,otherwise false
	 */
	public boolean isgetGreen() {
		if(checkcollisions(pacman.getRect(),power.getRect())){
			ghosts.isGreen = true;
			getpowertime = System.currentTimeMillis();
		}
		return ghosts.isGreen;
	}
	/**
	 * this method check the collision between panman and ghosts,
	 * and decide which will die(then does other operation)
	 */
	private void check() {
		if(!ghosts.isGreen) {
			isgetGreen();
			if(checkcollisions(pacman.getRect(),ghosts.getRect())) {
				pacman.died();
			}
		}
		else if(ghosts.isGreen) {
			double endpowertime =  System.currentTimeMillis();
			if((int)((endpowertime - getpowertime)/1000) >= 5 ) {
				ghosts.isGreen = false;
			}
			if(checkcollisions(pacman.getRect(),ghosts.getRect())) {
				int n = ghosts.died(pacman.getRect());
				if(n == 3) {
					score = score + 200;
				}
				else if(n == 2) {
					score = score + 400;
				}
				else if(n == 1) {
					score = score + 800;
				}
				else if(n == 0) {
					score = score + 1600;
				}
			}
		}
	}
	/**
	 * this method updates the scores
	 * //dots 10
		// power 50
		// fruit 500
		//ghosts 200 400 800 1600
	 */
	private void updateScore() {
		if(dots.checkCollisions(pacman.getRect())) {
			score = score + 10;
		}
		if(fruits.checkCollisions(pacman.getRect())) {
			score = score + 500;
		}
		if(power.checkCollisions(pacman.getRect())) {
			score = score + 200;
		}
	}
	
	/**
	   * This method is where the game is updated.
	   * This includes making changing the locations of the characters, checking for collisions 
	   * (between pacman and ghosts or pellets etc.) and updating the score.
	   */
	public void updateGame() {
		movePacman();// move player
		moveGhosts();// move ghosts
        check();// detect collisions
	    updateScore();// update score
	}
	/**
	   * This method is where the game is reset,
	   * This includes resetting everything on the screen	  
	   */
	private void resetGame(PlayerListener listener, GamemapScreen g) {
		this.gameScreen = g;
		g.addGame(this);
		this.listener = listener;
		walls = new Walls(level[currentLevel].getstring());
		pacman = new PacMan(listener,walls.getRect());
		ghosts = new Ghosts(level[currentLevel].getstring(),walls.getRect(),level[currentLevel].getSpeed());
		dots = new Dots(level[currentLevel].getstring());
		fruits = new Fruits(level[currentLevel].getstring());
		power = new PowerPellets((level[currentLevel].getstring()));
	}

	public void run() {
		double now = System.nanoTime();
	    double lastUpdateTime = System.nanoTime();
	    double lastRenderTime = System.nanoTime();
	    
	    while(playerLives > 0 && (dots.getDotsRemaining() >0 || power.getPowerRemaining() >0)) {
	    	if(!pacman.isdied() && !pause) {
	    		int updateCount = 0;
	    		while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
	    	        updateGame();
	    	        lastUpdateTime += TIME_BETWEEN_UPDATES;
	    	        updateCount++;
	    	    }
	    		gameScreen.paintImmediately(SCREEN_BOUNDS); //This repaints the screen 
	    		lastRenderTime = now;
	    		
	    		while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS) {
	    	        try { // This loop will cause the game to sleep until it is time to update the game
	    	            Thread.sleep(1);
	    	        } catch (Exception e) {}
	    	        now = System.nanoTime();
	    	    }
	    		if (listener.isPlayPause()) {
					pause = true;
				}
	    	}
	    	else {
	    		lastUpdateTime = System.nanoTime();
	    		if (listener.isPlayPause()) {
					pause = false;
				}
	    		if(pacman.isdied()) {
	    			pause = true;
	    			pacman.resetpacman();
	    			playerLives = playerLives - 1;
	    		}
	    		gameScreen.paintImmediately(SCREEN_BOUNDS);
	    		try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
				}
	    	}
	    }
	  //level ++
	    if(playerLives > 0 && (dots.getDotsRemaining() ==0 && power.getPowerRemaining() ==0) ) {
	    	currentLevel++;
	    	if (currentLevel < 2) {
	    		resetGame(listener,gameScreen);
				run();
			}
	    }
	    if (listener.isPlayPause()) {
			pause = !pause;
		}
	    
	}
	/**
	 * this method is used to get the level of the game
	 * @return return the level of the game
	 */
	public int getLevel() {
		return currentLevel+1;
	}
	/**
	 * this method is used to get the Score of the game
	 * @return return the Score of the game
	 */
	public int getScore() {
		return score;
	}
	/**
	 * this method is used to get the playerLives
	 * @return return the playerLives
	 */
	public int getLives() {
		return playerLives;
	}
	/**
	 * this method is used to get the pacman,
	 * @return return  pacman
	 */
	public PacMan getPacMan() {
		return pacman;
	}
	/**
	 * this method is used to get the ghosts,
	 * @return return  ghosts
	 */
	public Ghosts getGhosts1() {
		return ghosts;
	}
	
}
