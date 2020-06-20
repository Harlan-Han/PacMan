package Screen;

import javax.swing.JFrame;
import keyboardControl.PlayerListener;
/**
*  @author Han Houzhi 18206155
*this is the MainMenu screen,
*player will see this screen first 
*/

public class Window {
	public static final String GAME_NAME ="PACMAN";
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 800;
	/**
	 * start by this screen.
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setSize(Window.SCREEN_WIDTH, Window.SCREEN_HEIGHT);//窗口大小
		window.setTitle(GAME_NAME);//窗口标题
		window.setResizable(false);// 设置窗口是否可放大缩小
		window.setLocationRelativeTo(null);// 把窗口位置设置到屏幕的中心
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗口关闭按钮的默认操作(点击关闭时退出进程)
		PlayerListener listener = new PlayerListener();
		window.addKeyListener(listener);
		
		MainMenu menu = new MainMenu(window, listener);
		window.setVisible(true);
		window.requestFocusInWindow();
		menu.run();
	}
}
