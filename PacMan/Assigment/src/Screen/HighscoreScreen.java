package Screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import Score.ScoreKeeper;
import Score.ScoreKeeper.Score;
import keyboardControl.PlayerListener;
/**
 *  @author Han Houzhi
*this is the highest score screen,
*it  shows the top ten highest score
 */



public class HighscoreScreen extends JPanel{
	private static final long serialVersionUID = 1616386874546775416L;

	private boolean menu = false;
	private PlayerListener listener;
	private ScoreKeeper scoreKeeper;
	/**
	 * get the scores
	 * @param scoreKeeper this controls the scores
	 * @param l this let player can change the window
	 */
	public HighscoreScreen(ScoreKeeper scoreKeeper, PlayerListener l) {	
		this.scoreKeeper = scoreKeeper;
		this.listener = l;
	}
/**
 *set the font ,place and color of the text 
 * @param g paint bush
 * @param text the scores
 * @param rect the rectangle where the scores hold
 * @param size the size of showing scores
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
 * show the scores on the screen,
 * show some prompt message
 */
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Window.SCREEN_WIDTH, Window.SCREEN_HEIGHT);
		
		g.setColor(Color.GREEN);
		drawString(g, "PACMAN Hall of Fame", new Rectangle(0, 0, Window.SCREEN_WIDTH, Window.SCREEN_HEIGHT / 8),
				36);
		
		Score[] scores = scoreKeeper.getScores();
		g.setFont(new Font("Arial", Font.BOLD, 24));
		for (int i = 0; i < scores.length; i++) {
			Score score = scores[i];
			g.drawString(score.getName(), 2 * Window.SCREEN_WIDTH / 6, 96 + i * 32);
			g.drawString("" + score.getScore(), 4 * Window.SCREEN_WIDTH / 6, 96 + i * 32);
		}
		
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
