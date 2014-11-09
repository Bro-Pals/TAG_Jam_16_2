package bropals.tag16_2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Frame;
import java.awt.Point;
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
	
	public GameWindow() {
		mousePositionX = 0;
		mousePositionY = 0;
		mouseBufferX = 0;
		mouseBufferY = 0;
		mousePressed = false;
		frame = new Frame("Ironclad vs. Sea monsters");
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseBufferX = e.getX()-frame.getInsets().left;
				mouseBufferY = e.getY()-frame.getInsets().top;
				mousePressed = true;
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mouseBufferX = e.getX()-frame.getInsets().left;
				mouseBufferY = e.getY()-frame.getInsets().top;
				mousePressed = false;
			}
		});
		frame.setIgnoreRepaint(true);
		frame.setVisible(true);
		frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		frame.setSize(
			SCREEN_WIDTH+frame.getInsets().left+frame.getInsets().right,
			SCREEN_HEIGHT+frame.getInsets().top+frame.getInsets().bottom
		);
		frame.setLocationRelativeTo(null);
	}

	/**
		Updates the reported mouse position
	*/
	public void updateMousePosition() {
		Point p = frame.getMousePosition();
		mousePositionX = p.x-frame.getInsets().left;
		mousePositionY = p.y-frame.getInsets().top;
	}
	
	public static GameWindow getGameWindow() {
		return window;
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
	
	
}