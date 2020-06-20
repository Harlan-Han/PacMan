package Object;
/**
 *  @author Deng PengCheng 18206167
 * this class is used to create walls
 */
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

public class Walls {
	public class Wall {
		private int x;
		private int y;
		private int width;
		private int height;
		private Rectangle wall;
		/**
		 * set the place of walls.
		 * its weight and height will be 20.
		 * @param xx set the place of walls
		 * @param yy set the place of walls
		 */
		public Wall(int xx, int yy) {
			x = xx;
			y = yy;
			width = 20;
			height = 20;
		    wall = new Rectangle(x, y, width, height);
		 }
		/**
		 * get the shape of a single wall
		 * @return return rectangle of a single wall
		 */
		public Shape getShape() {
		      return new Rectangle(wall);
		}
		/**
		 * get the rectangle of a single wall
		 * @return return rectangle of a single wall
		 */
		public Rectangle getRect() {
			return new Rectangle(wall);
		}
	}
	
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private Wall wall;
	/**
	 * set the place of whole walls
	 * @param s this is from text which determines the where the walls are 
	 */
	public Walls(int[] s) {
		int i =0 ;
        for(int y = 40; y!=520 ;y = y+20) {
        	for(int x = 0; x!=400 ; x = x+20) {
        		if(s[i] == 37) {//%
        			wall = new Wall(x,y);
        			walls.add(wall);
        		}
        		i++;
        	}
        }
	}
	/**
	 * this method is used in painting walls.
	 * used {@code getShape}
	 * @return return the list of walls
	 */
	public List<Shape> getShapes() {
		List<Shape> ww = new ArrayList<Shape>();
		for (Wall w : walls) {
		    ww.add(w.getShape());
		}
		return ww;
    }
	/**
     * get the rectangle of all walls.
     * this method is used in checking collision.
     * used {@code getRect}.
     * @return return the list of the rectangles of all walls
     */
	public List<Rectangle> getRect() {
		List<Rectangle> ww = new ArrayList<Rectangle>();
		for (Wall w : walls) {
		    ww.add(w.getRect());
		}
		return ww;
    }

}
