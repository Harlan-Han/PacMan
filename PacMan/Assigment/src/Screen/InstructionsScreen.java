package Screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import keyboardControl.PlayerListener;

/**
 *  @author Han Houzhi
*this is the InstructionsScreen screen,
*it  shows the help information
 */

public class InstructionsScreen extends JPanel{
	private static final long serialVersionUID = -1264717778772722118L;
	
	private boolean menu = false;
	private PlayerListener listener;
	
	 /**
	  * this method help player change window
	  * @param pl
	  */
	public InstructionsScreen(PlayerListener pl) {
		listener = pl;
	}
	/**
	 *set the font ,place and color of the text 
	 * @param g paint bush
	 * @param text the Instructions
	 * @param rect the rectangle where the Instructions hold
	 * @param size the size of showing Instructions
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
	 * show some prompt message
	 */
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.SCREEN_WIDTH, Window.SCREEN_HEIGHT);
		
		g.setColor(Color.green);
		drawString(g, "PACMAN Controlls", new Rectangle(0, 0, Window.SCREEN_WIDTH, 64), 36);
		
		g.drawString("Move Left", 1 * Window.SCREEN_WIDTH / 6, 96 + 0 * 32);
		g.drawString("left arrow", 4 * Window.SCREEN_WIDTH / 6, 96 + 0 * 32);
		g.drawString("Move Right", 1 * Window.SCREEN_WIDTH / 6, 96 + 1 * 32);
		g.drawString("right arrow", 4 * Window.SCREEN_WIDTH / 6, 96 + 1 * 32);
		g.drawString("Move Up", 1 * Window.SCREEN_WIDTH / 6, 96 + 2 * 32);
		g.drawString("up arrow", 4 * Window.SCREEN_WIDTH / 6, 96 + 2 * 32);
		g.drawString("Move Down", 1 * Window.SCREEN_WIDTH / 6, 96 + 3 * 32);
		g.drawString("down arrow", 4 * Window.SCREEN_WIDTH / 6, 96 + 3 * 32);
		g.drawString("Play/Pause", 1 * Window.SCREEN_WIDTH / 6, 96 + 4 * 32);
		g.drawString("p", 4 * Window.SCREEN_WIDTH / 6, 96 + 4 * 32);
		
		drawString(g, "Press 'M' to return to the Main Menu", new Rectangle(0, 416, Window.SCREEN_WIDTH, 96), 24);
	}
	/**
	 * get the state of window to determine when to show this screen
	 */
	public void run() {
		while (!menu) {
			if (listener.isMenu()) {
				menu = true;
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
		}
		listener.reset();
		menu = false;
	}

}
