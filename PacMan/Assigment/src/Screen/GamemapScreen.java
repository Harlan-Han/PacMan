package Screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JComponent;

import Object.Ghosts;
import Outline.Game;
/**
 *  @author Deng pengcheng 18206167
*this is the map screen,
*it draws maze , pacman and ghosts,
*it also shows level ,score and PlayerLife
 */



public class GamemapScreen extends JComponent {
	private static final long serialVersionUID = -8282302849760730222L;
	private Game game;
	private double time1 = System.currentTimeMillis();
	/**
	 * this method  draws maze , pacman and ghosts,
	 *it also shows level ,score and PlayerLife
	 */
	@Override
    protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
	    g.fillRect(0, 0, Window.SCREEN_WIDTH, Window.SCREEN_HEIGHT);
	    g.setColor(Color.green);
	    g.setFont(new Font("Arial", Font.BOLD, 24));
		g.drawString("Lives: " + game.getLives(), 500, 100);
		g.drawString("Score: " + game.getScore(), 500, 200);
		g.drawString("Level: " + game.getLevel(), 500, 300);
		
		Graphics2D g2 = (Graphics2D) g;
		//Draw walls
		g.setColor(Color.GRAY);
		for (Shape s : game.getWalls()) {
			g2.fill(s);
	    }
		//Draw pacman
		g.setColor(Color.YELLOW);
		game.getPacMan().drawSelf(g2);
		//Draw dots
		g.setColor(Color.WHITE);
		for (Shape s : game.getDots()) {
			g2.fill(s);
	    }
		//Draw fruits
		g.setColor(Color.GREEN);
		double time2 = System.currentTimeMillis();
		if(((int)(time2-time1)/1000)>10) {
			g.setColor(Color.BLACK);
		}
		if(((int)(time2-time1)/1000)>15) {
			g.setColor(Color.GREEN);
		}
		if(((int)(time2-time1)/1000)>5){
			for (Shape s : game.getFruits()) {
				g2.fill(s);
		    }
		}
		//Draw power
		g.setColor(Color.ORANGE);
		for (Shape s : game.getPowerPellets()) {
			g2.fill(s);
	    }
		//Draw Ghosts
		if(game.isgetGreen()) {
			g.setColor(Color.GREEN);
		}
		else {
			g.setColor(Color.RED);
		}
		for (Ghosts.Ghost s : game.getGhosts1().getGhost()) {	
			s.drawSelf(g);
	    }

		g.setColor(Color.green);
		if ((game.isPaused() || game.getPacMan().isdied() ) && (game.getLives() > 0)) {
			g.drawString("Press 'p' to continue ", 500, 400);
		} else if (game.getLives() == 0) {
			g.drawString("Game over ", 500, 400);
			g.drawString("After you entered your name,", 100, 600);
			g.drawString("You can Press M and then Press H to check your rank!",100,650);
		}
	}
/**
 * add it to the game
 * @param g pass the map
 */
	public void addGame(Game g) {
		this.game = g;
	}
	
}
