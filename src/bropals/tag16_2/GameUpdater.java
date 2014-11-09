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
	}
	
	public void updateGame() {
		
	}
	
	public void drawGame() {
		
	}
	
	public void loop() {
		running = true;
		long before, delta;
		while(running) {
			before = System.currentTimeMillis();
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