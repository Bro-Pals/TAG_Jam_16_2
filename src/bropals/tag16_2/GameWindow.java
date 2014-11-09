package bropals.tag16_2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferStrategy;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.Frame;
import java.awt.Point;
import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.MouseInfo;

public class GameWindow {
	
	public static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;
	private static GameWindow window = new GameWindow();
	
	private Frame frame;
	
	private int mousePositionX;
	private int mousePositionY;
	private int mouseBufferX;
	private int mouseBufferY;
	private boolean mousePressed;
	private int mouseButton;
	private BufferStrategy bs;
	
	public GameWindow() {
		mousePositionX = 0;
		mousePositionY = 0;
		mouseBufferX = 0;
		mouseBufferY = 0;
		mouseButton = -1;
		mousePressed = false;
		bs = null;
		frame = new Frame("Ironclad vs. Sea monsters");
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseBufferX = e.getX()-frame.getInsets().left;
				mouseBufferY = e.getY()-frame.getInsets().top;
				mousePressed = true;
				mouseButton = e.getButton();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseBufferX = e.getX()-frame.getInsets().left;
				mouseBufferY = e.getY()-frame.getInsets().top;
				mousePressed = false;
				mouseButton = e.getButton();
			}
		});
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setIgnoreRepaint(true);
		frame.setVisible(true);
		frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

	public Graphics getDrawGraphics() {
		if (bs==null) {
			frame.createBufferStrategy(2);
			bs = frame.getBufferStrategy();
		}
		return bs.getDrawGraphics();
	}
	
	public void swapBuffers(Graphics g) {
		g.dispose();
		bs.show();
	}
	
	/**
		Updates the reported mouse position
	*/
	public void updateMousePosition() {
		Point p = frame.getMousePosition();
		if (p != null) { // it has the possibility of returning null
			mousePositionX = p.x;//-frame.getInsets().left;
			mousePositionY = p.y;//-frame.getInsets().top;
		}
	}
	
	public static GameWindow getGameWindow() {
		return window;
	}
	
	public int getInsetsX() {
		return frame.getInsets().left;
	}
	
	public int getInsetsY() {
		return frame.getInsets().right;
	}
	
	public int getMousePositionX() {
		return mousePositionX;
	}
	
	public int getMousePositionY() {
		return mousePositionY;
	}
	
	public int getMouseBufferX() {
		return mouseBufferX;
	}
	
	public int getMouseBufferY() {
		return mouseBufferY;
	}
	
	public boolean getMousePressed() {
		return mousePressed;
	}
	
	public int getMouseButton() {
		return mouseButton;
	}
}