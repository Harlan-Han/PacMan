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
		window.setSize(Window.SCREEN_WIDTH, Window.SCREEN_HEIGHT);//���ڴ�С
		window.setTitle(GAME_NAME);//���ڱ���
		window.setResizable(false);// ���ô����Ƿ�ɷŴ���С
		window.setLocationRelativeTo(null);// �Ѵ���λ�����õ���Ļ������
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ô��ڹرհ�ť��Ĭ�ϲ���(����ر�ʱ�˳�����)
		PlayerListener listener = new PlayerListener();
		window.addKeyListener(listener);
		
		MainMenu menu = new MainMenu(window, listener);
		window.setVisible(true);
		window.requestFocusInWindow();
		menu.run();
	}
}
