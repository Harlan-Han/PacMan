package Screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;
/**
*  @author Han Houzhi
*this is the MainMenu screen,
*player will see this screen first 
*/
public class MenuScreen extends JPanel{
	  private static final long serialVersionUID = 1616386874546775416L;
	  /**
	   *set the font ,place and color of the text 
	   * @param g paint bush
	   * @param text the text (windows' name)
	   * @param rect the rectangle where the text hold
	   * @param size the size of showing text
	   */
	  private void drawString(Graphics g, String text, Rectangle rect, int size) {
		    Graphics2D g2d = (Graphics2D) g.create();

		    Font font = new Font("Arial", Font.BOLD, size);
		    g2d.setFont(font);
		    FontMetrics metrics = g2d.getFontMetrics();
		    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

		    g2d.setColor(Color.GREEN);
		    g2d.drawString(text, x, y);

	  }
	  /**
	   * show the strings on the screen,
	   * show some prompt message
	   */
	  public void paintComponent(Graphics g) {
		    g.setColor(Color.BLACK);
		    g.fillRect(0, 0, Window.SCREEN_WIDTH, Window.SCREEN_HEIGHT);
		    drawString(g, "Welcome to PACMAN!!!", new Rectangle(Window.SCREEN_WIDTH / 3, Window.SCREEN_HEIGHT / 8, Window.SCREEN_WIDTH / 3, Window.SCREEN_HEIGHT / 3), 24);
		    drawString(g, "To play a game press N", new Rectangle(Window.SCREEN_WIDTH / 3, 2 * Window.SCREEN_HEIGHT / 8, Window.SCREEN_WIDTH / 3, Window.SCREEN_HEIGHT / 3), 18);
		    drawString(g, "To see the High scores press H", new Rectangle(Window.SCREEN_WIDTH / 3, 3 * Window.SCREEN_HEIGHT / 8, Window.SCREEN_WIDTH / 3, Window.SCREEN_HEIGHT / 3), 18);
		    drawString(g, "For help press A", new Rectangle(Window.SCREEN_WIDTH / 3, 4 * Window.SCREEN_HEIGHT / 8, Window.SCREEN_WIDTH / 3, Window.SCREEN_HEIGHT / 3), 18);
		    drawString(g, "To exit press X", new Rectangle(Window.SCREEN_WIDTH / 3, 5 * Window.SCREEN_HEIGHT / 8, Window.SCREEN_WIDTH / 3, Window.SCREEN_HEIGHT / 3), 18);

	  }
	  
}
