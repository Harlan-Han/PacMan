package Screen;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Outline.Game;
import Score.PersistentScoreKeeper;
import Score.ScoreKeeper;
import keyboardControl.PlayerListener;

/**
 *  @author Han Houzhi
*this is the MainMenu screen,
*player can start a new game or see other information here
 */


public class MainMenu {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -207820671704916628L;
	private Game game;
	private JFrame window;
	private PlayerListener listener;
	private MenuScreen menuScreen ;
	private GamemapScreen gameScreen ;
	private HighscoreScreen scoreScreen ;
	private InstructionsScreen instructionsScreen ;
	private CardLayout cardLayout = new CardLayout();
	private JPanel main = new JPanel(cardLayout);
	private boolean exit;
	private ScoreKeeper scoreKeeper;
/**
 * this method add other screen ,
 * ready to open other screen
 * @param win JFrame(create window)
 * @param lis PlayerListener(help change window)
 */
	public MainMenu(JFrame win, PlayerListener lis) {
		this.window = win;
		this.listener = lis;
		menuScreen = new MenuScreen();
		gameScreen = new GamemapScreen();
		scoreKeeper = new PersistentScoreKeeper();
		scoreScreen = new HighscoreScreen(scoreKeeper,listener);
		instructionsScreen = new InstructionsScreen(listener);
		main.add(menuScreen, "MainMenu Screen");
		main.add(gameScreen, "Game Screen");
		main.add(scoreScreen, "HighScores Screen");
		main.add(instructionsScreen, "Instructions Screen");
		window.getContentPane().add(main);
	}

	/**
	 * this method opens windows (decided by player)
	 */
	public void run() {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(Long.MAX_VALUE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		while (!exit) {
			if (listener.isNewGame()) {
				game = new Game(listener, gameScreen);
				cardLayout.show(main, "Game Screen");
				game.run();
				int s = game.getScore();
				if (s > scoreKeeper.getLowestScore()) {
					String name = JOptionPane.showInputDialog("New High Score, Please enter your name:");
					scoreKeeper.addScore(name, s);
					cardLayout.show(main, "High Scores");
					scoreScreen.run();
					cardLayout.show(main, "Main Menu");
					listener.reset();
				} //start the game engine
			} else if (listener.isHigh()) {
				cardLayout.show(main, "HighScores Screen");
				scoreScreen.run();
				cardLayout.show(main, "MainMenu Screen");
			} else if (listener.isAbout()) {
				cardLayout.show(main, "Instructions Screen");
				instructionsScreen.run();
				cardLayout.show(main, "MainMenu Screen");
			}else if (listener.isExit()) {
				exit = true;
				scoreKeeper.saveScores();
				System.exit(0);
			} 
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		exit = false;
	}
}
