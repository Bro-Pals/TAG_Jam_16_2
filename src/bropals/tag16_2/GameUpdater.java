package bropals.tag16_2;

import bropals.tag16_2.creature.*;
import java.util.ArrayList;

public class GameUpdater {
	
	private ArrayList<BaseCreature> enemies;
	private Ironclad ironclad;
	
	private boolean running;
	private final long mpf = 20;
	
	public GameUpdater() {
		running = false;
		enemies = new ArrayList<BaseCreature>();
	}
	
	public void updateGame() {
		for (int i=0; i<enemies.size(); i++) {
			BaseCreature bc = enemies.get(i);
			bc.update();
		}
	}
	
	public void drawGame() {
		
		for (int i=0; i<enemies.size(); i++) {
			BaseCreature bc = enemies.get(i);
			
		}
	}
	
	public void loop() {
		running = true;
		long before, delta;
		while(running) {
			before = System.currentTimeMillis();
			//Upate the value
			GameWindow.getGameWindow().updateMousePosition();
			updateGame();
			drawGame();
			delta = System.currentTimeMillis()-before;
			if (delta < mpf) {
				try {
					Thread.sleep(mpf - delta);
				} catch(Exception e) {
					
				}
			}
		}
	}
}