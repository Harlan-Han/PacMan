package Object;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

/**
 *  @author Han HouZhi 18206155
 * this class is about fruits
 * fruits can be ate to let pacman be able to eat ghosts
 */

public class Fruits {
	class Fruit {
		private int x;
		private int y;
		private int width;
		private int height;
		private Rectangle fruit;
		/**
		 * create fruits
		 * @param x width of fruits
		 * @param y height of fruits
		 */
		public Fruit(int x, int y){
			this.x = x;
			this.y = y;
			width = 6;
			height = 8;
			fruit = new Rectangle(x, y, width, height);
		}
		/**
		 * get the shape of a single fruit
		 * @return return rectangle of a single fruit
		 */
	    public Shape getShape() {
	        return new Rectangle(fruit);
	    }
	    /**
		 * get the rectangle of a single fruit
		 * @return return rectangle of a single fruit
		 */
	    public Rectangle getRect() {
	        return new Rectangle(x,y,width,height);
	    }
	    /**
	     * Check if fruits collides with pacman
	     * @param pm it is pacman
	     * @return  return ture if there is a collision with panman,otherwise false
	     */
	    public boolean checkcollisions(Rectangle pm) {
	        return fruit.intersects(pm);
	      }
	    
	}
	
	private ArrayList<Fruit> fruits = new ArrayList<Fruit>();
	private Fruit fruit;
	/**
	 * create fruits and determine their place
	 * @param s this is from text which determines the where the fruits are 
	 */
	public Fruits(int[] s) {
		int i =0 ;
        for(int y = 40; y!=520 ;y = y+20) {
        	for(int x = 0; x!=400 ; x = x+20) {
        		if(s[i] == 70) {//F
        			fruit = new Fruit(x+5,y+5);
        			fruits.add(fruit);
        		}
        		i++;
        	}
        }
	}	
	
	/**
	 * this method is used in painting all fruits.
	 * used {@code getShape}.
	 * @return return the list of fruits
	 */
	public List<Shape> getShapes() {
		List<Shape> dd = new ArrayList<Shape>();
		for (Fruit d : fruits) {
		    dd.add(d.getShape());
		}
		return dd;
    }
	/**
     * get the rectangle of all fruits.
     * this method is used in checking collision.
     * used {@code getRect}.
     * @return return the list of the rectangles of all fruits
     */
	public List<Rectangle> getRect() {
		List<Rectangle> dd = new ArrayList<Rectangle>();
		for (Fruit d : fruits) {
		    dd.add(d.getRect());
		}
		return dd;
    }
	/**
     * Check if fruits collides with pacman,if it does,remove the fruits,
     * @param pm it is pacman
     * @return whether  collide with pacman or not
     */
	public boolean checkCollisions(Rectangle pm) {
		boolean hit = false;
		Fruit remove = null;
		for (Fruit br : fruits) {
		    if (br.checkcollisions(pm)) {
		        remove = br;
		        hit = true;
		    }
	    }
		fruits.remove(remove);
		return hit;
    }
}
