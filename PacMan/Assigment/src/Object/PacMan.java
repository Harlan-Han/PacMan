package Object;
/**
 *  @author Han HouZhi 18206155
 * this class is about pacman
 * users can control pacman
 *pacman can die and reset
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.util.List;

import Outline.Game;
import keyboardControl.PlayerListener;

public class PacMan {
	private int x;
	private int y;
	private int width;
	private int height;
	private int speed;
	private Rectangle pacman;
	private int direction = 3 ;
	private List<Rectangle> walls;
	private PlayerListener listener;
	/**
	 * create pacman 
	 * width and height are already set
	 * @param l {@code PlayerListener}user control it
	 * @param w this is wall
	 */
	public PacMan(PlayerListener l,List<Rectangle> w) {
		listener= l;
		walls=w;
		x=20;
		y=60;
		width = 20;
		height = 20;
		speed = 5;
		pacman = new Rectangle(x,y,width,height);
	}
	/**
	 * paint pacman on screen
	 * @param g paint bush
	 */
	public void drawSelf(Graphics2D g) {
		g.setColor(Color.YELLOW);
		if(getDirection() == 3) {//right
			g.fillArc(x, y, width, height, 45, 270);
		}
		else if(getDirection() == 2) {//left
			g.fillArc(x, y, width, height, 225, 270);
		}
		else if(getDirection() == 0) {//up
			g.fillArc(x, y, width, height, 135, 270);
		}
		else if(getDirection() == 1) {//down
			g.fillArc(x, y, width, height, 315, 270);
		}
	}
	/**
	 * check collision with walls
	 * @param pm rectangle of pacman
	 * @param el this is walls
	 * @return return true if pacman collides with wall,otherwise false
	 */
	private boolean checkcollisions(Rectangle pm,List<Rectangle> el){
    	boolean iscollisions = false;
    	for (Rectangle s : el) {
		    if(pm.intersects(s)) {
		    	iscollisions = true;
		    }
	    }
    	return iscollisions;
    }
	/**
	 * get the direction pacman will move to
	 * @return return numbers represent direction
	 */
	public int getDirection() {
		if (listener.left) {
	    	direction = 2 ;
	    } else if (listener.right) {
	    	direction = 3 ;
	    } else if (listener.up) {
	    	direction = 0 ;
	    } else if (listener.down) {
	    	direction = 1 ;
	    }
		return direction;
	}
	/**
	 * control how pacman moves
	 */
	public void move() {
		Rectangle newBody = new Rectangle(this.x, this.y, width, height);
		if (Game.getScreenBounds().contains(newBody.getBounds())) {
			pacman = newBody;
			switch(getDirection()) {
			case 0:
				y = y - speed ;
				break;
			case 1:
				y = y + speed ;
				break;
			case 2:
				x = x - speed ;
				break;
			case 3: 
				x = x + speed ;
				break;
			}
			if(checkcollisions(getRect(),walls)) {
				switch(getDirection()) {
				case 0:
					y = y + speed ;
					break;
				case 1:
					y = y - speed ;
					break;
				case 2:
					x = x + speed ;
					break;
				case 3: 
					x = x - speed ;
					break;
				}
			}
		}
	}
	/**
	 * this method is used to get the rectangle of pacman
	 * used in collision
	 * @return return rectangle of pacman
	 */
	public Rectangle getRect() {
		return new Rectangle(x,y,width,height);
	}
	
	boolean die = false;
	/**
	 * this method set the"died" state of pacman
	 */
	public void died() {
		die = true;
	}
	/**
	 * check if pacman is died
	 * @return true if pacman is died,otherwise false
	 */
	public boolean isdied() {
		return die;
	}
	/**
	 * reset pacman after it died and still
	 */
	public void resetpacman() {
		x=20;
		y=60;
		die = false;
	}

}














