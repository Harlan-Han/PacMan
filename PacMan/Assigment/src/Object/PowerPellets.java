package Object;

/**
 *  @author Han HouZhi 18206155
 * this class is about PowerPellets
 * users can control pacman to eat PowerPellets to get higher scores
 */
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;


public class PowerPellets {
	class  PowerPellet{
		private int x;
		private int y;
		private int width;
		private int height;
		private Rectangle powerPellet;
		/**
		 * set the place of pellet and create its rectangle
		 * @param x  set x of pellet
		 * @param y  set y of pellet
		 */
		public PowerPellet(int x, int y){
			this.x = x;
			this.y = y;
			width = 10;
			height = 10;
			powerPellet = new Rectangle(x, y, width, height);
		}
		/**
		 * this method is used in painting power pellet
		 * @return return the rectangle of power pellet
		 */
	    public Shape getShape() {
	        return new Rectangle(powerPellet);
	    }
	    /**
	     * get the rectangle of power pellet
	     * @return return the rectangle of power pellet
	     */
	    public Rectangle getRect() {
	        return new Rectangle(x,y,width,height);
	    }
	    /**
	     * Check if  power pellet collides with pacman
	     * @param pm it is pacman
	     * @return return true if it collides with pacman,otherwise false
	     */
	    public boolean checkcollisions(Rectangle pm) {
	        return powerPellet.intersects(pm);
	    }
	}
	
	private ArrayList<PowerPellet> powerPellets = new ArrayList<PowerPellet>();
	private PowerPellet powerPellet;

	/**
	 * set the place of all power pellets
	 * @param s  this is from text which determines the where the pellets are 
	 */
	public PowerPellets(int[] s) {
		int i =0 ;
        for(int y = 40; y!=520 ;y = y+20) {
        	for(int x = 0; x!=400 ; x = x+20) {
        		if(s[i] == 42) {//*
        			powerPellet = new PowerPellet(x+3,y+3);
        			powerPellets.add(powerPellet);
        		}
        		i++;
        	}
        }
	}	
	/**
	 * this method is used in painting power pellets.
	 * used {@code getShape}
	 * @return return the list of power pellets
	 */
	public List<Shape> getShapes() {
		List<Shape> dd = new ArrayList<Shape>();
		for (PowerPellet d : powerPellets) {
		    dd.add(d.getShape());
		}
		return dd;
    }
	 /**
     * get the rectangle of power pellet.
     * this method is used in checking collision.
     * used {@code getRect}
     * @return return the list of the rectangles of power pellets
     */
	public List<Rectangle> getRect() {
		List<Rectangle> dd = new ArrayList<Rectangle>();
		for (PowerPellet d : powerPellets) {
		    dd.add(d.getRect());
		}
		return dd;
    }
	/**
     * Check if  power pellet collides with pacman,if it does,remove the fruits,
     * @param pm it is pacman.
     * used {@code checkcollisions}
     * @return return true if it collides with pacman,otherwise false
     */
	public boolean checkCollisions(Rectangle pm) {
	    boolean hit = false;
		PowerPellet remove = null;
		for (PowerPellet br : powerPellets) {
		    if (br.checkcollisions(pm)) {
		        remove = br;
		        hit = true;
		    }
	    }
		powerPellets.remove(remove);
		return hit;
	}
	/**
	 * to see how many power pellets remain
	 * @return  return the number of dots remained
	 */
	public int getPowerRemaining() {
		return powerPellets.size();
	}
}
