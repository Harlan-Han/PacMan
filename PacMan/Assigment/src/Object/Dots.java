package Object;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
/**
 *  @author Han HouZhi 18206155
 * this class is about dots
 * dots can be ate to increase scores
 */


public class Dots {
	class Dot {
		private int x;
		private int y;
		private int width;
		private int height;
		private Rectangle dot;
		/**
		 *  width and weight of dots will be 2
		 * @param x the width of dot
		 * @param y the height of dot
		 */
		public Dot(int x, int y){
			this.x = x;
			this.y = y;
			width = 2;
			height = 2;
			dot = new Rectangle(x, y, width, height);
		}
		/**
		 * get the shape of a single dot
		 * @return return rectangle of a single dot
		 */
	    public Shape getShape() {
	        return new Rectangle(dot);
	    }
	    /**
		 * get the rectangle of a single dot
		 * @return return rectangle of a single dot
		 */
	    public Rectangle getRect() {
	        return new Rectangle(x,y,width,height);
	    }
	    /**
	     * Check if dots collides with pacman
	     * @param pm it is pacman
	     * @return return ture if there is a collision with panman,otherwise false
	     */
	    public boolean checkcollisions(Rectangle pm) {
	        return dot.intersects(pm);
	      }
	}
	
	private ArrayList<Dot> dots = new ArrayList<Dot>();
	private Dot dot;

/**
 * to create dots
 * @param s this is from text which determines the where the dots are 
 */
	public Dots(int[] s) {
		int i =0 ;
        for(int y = 40; y!=520 ;y = y+20) {
        	for(int x = 0; x!=400 ; x = x+20) {
        		if(s[i] == 46) {//.
        			dot = new Dot(x+9,y+9);
        			dots.add(dot);
        		}
        		i++;
        	}
        }
	}	
	/**
	 * this method is used in painting dots.
	 * used {@code getShape}.
	 * @return return the list of dots
	 */
	public List<Shape> getShapes() {
		List<Shape> dd = new ArrayList<Shape>();
		for (Dot d : dots) {
		    dd.add(d.getShape());
		}
		return dd;
    }
	/**
     * get the rectangle of all dots.
     * this method is used in checking collision.
     * used {@code getRect}.
     * @return return the list of the rectangles of all dots.
     */
	public List<Rectangle> getRect() {
		List<Rectangle> dd = new ArrayList<Rectangle>();
		for (Dot d : dots) {
		    dd.add(d.getRect());
		}
		return dd;
    }
	 /**
     * Check if dot collides with pacman,if it does,remove the dot,
     * @param pm it is pacman
     * @return whether  collide with pacman or not
     */
	public boolean checkCollisions(Rectangle pm) {
		boolean hit = false;
		Dot remove = null;
		for (Dot br : dots) {
		    if (br.checkcollisions(pm)) {
		        remove = br;
		        hit = true;
		    }
		}
		dots.remove(remove);
		return hit;
	}
	/**
	 * to see how many dots remain
	 * @return the number of dots remained
	 */
	public int getDotsRemaining() {
		return dots.size();
	}
	
}
