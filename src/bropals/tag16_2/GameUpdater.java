package bropals.tag16_2;

import java.util.ArrayList;
import bropals.tag16_2.projectile.*;
import bropals.tag16_2.creature.*;

public class GameUpdater {
	
	private ArrayList<BaseCreature> enemies;
	private ArrayList<Projectile> projectiles;
	private Ironclad ironclad;
	
	private boolean running;
	private final long mpf = 20;
	
	public GameUpdater() {
		running = false;
		enemies = new ArrayList<BaseCreature>();
		projectiles = new ArrayList<>();
	}
	
	public void updateGame() {
		for (int i=0; i<enemies.size(); i++) {
			BaseCreature bc = enemies.get(i);
			bc.update(enemies, ironclad, projectiles);
		}
		
		for (int i=0; i<projectiles.size(); i++) {
			projectiles.get(i).update(enemies, ironclad, projectiles);
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