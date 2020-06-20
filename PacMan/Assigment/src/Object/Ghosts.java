package Object;
/**
 *  @author Deng PengCheng 18206167
 * this class is used to create and control ghosts
 */
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import Outline.Game;

public class Ghosts {
	public class Ghost{
		private int x;
		private int y;
		private int width;
		private int height;
		private int speed;
		private int direction = 0;
		private Rectangle ghost;
		private List<Rectangle> walls;
		/**
		 *
		 * @param x determines the place of ghosts
		 * @param y determines the place of ghosts
		 * @param w this is the walls
		 * @param s this is speed of ghosts
		 */
		public Ghost(int x, int y,List<Rectangle> w,int s){
			this.x = x;
			this.y = y;
			walls = w;
			width = 20;
			height = 20;
			speed = s;
			ghost = new Rectangle(x, y, width, height);
		}
		/**
		 * draw ghosts on the screen
		 * @param g draw brush
		 */
		public void drawSelf(Graphics g) {
			g.fillOval(x+2, y, 16, 16);
			g.fillOval(x,y+15,5,5);
			g.fillOval(x+5,y+15,5,5);
			g.fillOval(x+10,y+15,5,5);
			g.fillOval(x+15,y+15,5,5);
		}
		/**
		 *  Check if ghosts collides with walls
		 * @param pm this is ghost
		 * @param el these are ghosts
		 * @return if ghosts collides with walls
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
		 * Check if ghosts collides with pacman
		 * @param pm this is pacman
		 * @return  return ture if there is a collision with panman,otherwise false
		 */
		public boolean checkpmcollisions(Rectangle pm) {
	        return ghost.intersects(pm);
	      }
		/**
		 * this method determines how ghosts move
		 * ghosts will move at the same speed
		 * g will avoid colliding with walls and choose direction randomly
		 */
		private void move() {
			Rectangle newBody = new Rectangle(this.x, this.y, width, height);
			if (Game.getScreenBounds().contains(newBody.getBounds())) {
				ghost = newBody;
				switch(direction) {
				case 0:
					y = y - speed ;
					break;
				case 1:
					x = x - speed ;
					break;
				case 2:
					y = y + speed ;
					break;
				case 3: 
					x = x + speed ;
					break;
				}
				if(checkcollisions(getRect(),walls)) {
					switch(direction) {
					case 0:
						y = y + speed ;
						break;
					case 1:
						x = x + speed ;
						break;
					case 2:
						y = y - speed ;
						break;
					case 3: 
						x = x - speed ;
						break;
					}
					double r = Math.random();
				    if (r<0.25) {
				    	direction = 0;
				    } else if (r>=0.25&&r<0.5) {
				    	direction = 1;
				    } else if (r>=0.5&&r<0.75) {
				    	direction = 2;
				    } else if (r>=0.75) {
				    	direction = 3;
				    }
				}
			}
			
		}
		/**
		 * get the shape of a single ghost.
		 * @return return the rectangle of a single ghost
		 */
        public Shape getShape() {
            return ghost;
        }
        /**
         * used in checking collision
         * @return the rectangle of a single ghost
         */
        public Rectangle getRect() {
            return new Rectangle(x,y,width,height);
        }
	}
	
	private ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
	private Ghost ghost;
	public boolean isGreen = false;
	/**
	 * Decide the place of ghosts
	 * @param s this is from text which determines the where the fruits are
	 * @param w this is wall
	 * @param speed ghosts will move at this speed
	 */
	public Ghosts(int[] s,List<Rectangle> w,int speed) {
		int i =0 ;
        for(int y = 40; y!=520 ;y = y+20) {
        	for(int x = 0; x!=400 ; x = x+20) {
        		if(s[i] == 71) {//G
        			ghost = new Ghost(x,y,w,speed);
        			ghosts.add(ghost);
        		}
        		i++;
        	}
        }
	}
	/**
	 * let ghosts start to move
	 */
	public void moveAll() {
		for (Ghost g : ghosts) {
		    g.move();
		}
	}
	/**
	 * this method is only used after pacman ate fruits
	 * check if colliding with pacman ,remove the ghost that collided with pacman
	 * 
	 * @param pm this is pacman
	 * @return return the size of ghosts
	 */
	public int died(Rectangle pm) {
		Ghost remove = null;
		for (Ghost br : ghosts) {
		    if (br.checkpmcollisions(pm)) {
		        remove = br;
		    }
		}
		ghosts.remove(remove);
		return ghosts.size();
	}
	/**
	 * used to get the whole ghosts
	 * used in painting ghosts
	 * @return return the list of ghosts
	 */
	public List<Ghost> getGhost(){
		List<Ghost> gg = new ArrayList<Ghost>();
		for (Ghost g : ghosts) {
		    gg.add(g);
		}
		return gg;
	}
	
	/**
     * get the rectangle of all ghosts.
     * this method is used in checking collision.
     * used {@code getRect}.
     * @return return the list of the rectangles of all ghosts
     */
	public List<Rectangle> getRect() {
		List<Rectangle> gg = new ArrayList<Rectangle>();
		for (Ghost g : ghosts) {
		    gg.add(g.getRect());
		}
		return gg;
    }
	
}
