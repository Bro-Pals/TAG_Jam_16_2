package bropals.tag16_2;

import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import bropals.tag16_2.projectile.*;
import bropals.tag16_2.creature.*;

public class GameUpdater {
	
	private ArrayList<BaseCreature> enemies;
	private ArrayList<Projectile> projectiles;
	private Ironclad ironclad;
	private AffineTransform at;
	
	private boolean running;
	private final long mpf = 20;
	
	public GameUpdater() {
		running = false;
		enemies = new ArrayList<BaseCreature>();
		projectiles = new ArrayList<>();
		at = new AffineTransform();
	}
	
	public void updateGame() {
		for (int i=0; i<enemies.size(); i++) {
			BaseCreature bc = enemies.get(i);
			bc.getAnimation().update(mpf);
			bc.update(enemies, ironclad, projectiles);
		}
		
		for (int i=0; i<projectiles.size(); i++) {
			projectiles.get(i).update(enemies, ironclad, projectiles);
		}
	}
	
	public void drawGame() {
		Graphics2D g = (Graphics2D)GameWindow.getGameWindow().getDrawGraphics();
		BaseCreature bc;
		BufferedImage image;
		float angle;
		for (int i=0; i<enemies.size(); i++) {
			at.setToIdentity();
			bc = enemies.get(i);
			image = bc.getAnimation().getCurrentFrame();
			angle = bc.getAngle();
			//  Rotate the image and then draw it  //
			at.rotate(angle);
			at.translate(bc.getX(), bc.getY());
			g.setTransform(at);
			g.drawImage(image, -(image.getWidth()/2), -(image.getHeight()/2), null);
		}
		GameWindow.getGameWindow().swapBuffers(g);
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