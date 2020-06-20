package keyboardControl;
/**
 *  @author Han HouZhi
 * this class is used to monitor the keyboard
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerListener implements KeyListener {
	public boolean left;
	public boolean right;
	public boolean up;
	public boolean down;
	private boolean newGame;
	private boolean high;
	private boolean about;
	private boolean menu;
	private boolean exit;
	private boolean pause;
/**
 * @param e it determines the direction of pacman
 * @see PacMan
 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
	}
	/**
	 * @param e it determines the direction of pacman
	 * @see PacMan
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
	}
/**
 * @param e it determines the operations related to window switching
 */
	public void keyTyped(KeyEvent e) {
		if (e.getExtendedKeyCode() == KeyEvent.VK_H || e.getKeyChar() == 'H'  || e.getKeyChar() == 'h') {
			high = true;
		} else if (e.getExtendedKeyCode() == KeyEvent.VK_X || e.getKeyChar() == 'X'  || e.getKeyChar() == 'x') {
			exit = true;
		} else if (e.getExtendedKeyCode() == KeyEvent.VK_M || e.getKeyChar() == 'M'  || e.getKeyChar() == 'm') {
			menu = true;
		} else if (e.getExtendedKeyCode() == KeyEvent.VK_N || e.getKeyChar() == 'N'  || e.getKeyChar() == 'n') {
			newGame = true;
		} else if (e.getExtendedKeyCode() == KeyEvent.VK_A || e.getKeyChar() == 'A'  || e.getKeyChar() == 'a') {
			about = true;
		} else if (e.getExtendedKeyCode() == KeyEvent.VK_P || e.getKeyChar() == 'P'  || e.getKeyChar() == 'p') {
			pause = true;
		} 
	}
	/**
	 * @return to check the state of window that was showing
	 */
	
	public boolean isNewGame() {
		if (newGame) {
			newGame = false;
			return true;
		}
		return false;
    }
	/**
	 * @return to check the state of window that was showing
	 */
	public boolean isHigh() {
		if (high) {
			high = false;
			return true;
		}
		return false;
	}
	/**
	 * @return to check the state of window that was showing
	 */
	public boolean isAbout() {
		if (about) {
			about = false;
			return true;
		}
		return false;
	}
	/**
	 * @return to check the state of window that was showing
	 */
	public boolean isExit() {
		if (exit) {
			exit = false;
			return true;
		}
		return false;
	}
	/**
	 * @return to check the state of window that was showing
	 */

    public boolean isMenu() {
		if (menu) {
			menu = false;
			return true;
		}
		return false;
	}
    /**
     * reset everything
     */
    public void reset() {
		left = false;
		right = false;
		up = false;
		down = false;
		
		newGame = false;
		high = false;
		about = false;
		exit = false;
	
		menu = false;
	}
    /**
	 * @return to check if the game is pausing
	 */  
    
	public boolean isPlayPause() {
		if (pause) {
			pause = false;
			return true;
		}
		return false;
	}

}