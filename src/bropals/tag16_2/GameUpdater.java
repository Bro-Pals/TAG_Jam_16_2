package bropals.tag16_2;

import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import bropals.tag16_2.projectile.*;
import bropals.tag16_2.creature.*;
import java.awt.Color;
import bropals.tag16_2.Animation;
import java.awt.event.MouseEvent;

public class GameUpdater {
	
	private ArrayList<BaseCreature> enemies;
	private ArrayList<Projectile> projectiles;
	private Ironclad ironclad;
	private AffineTransform at;
	private BufferedImage lake;
	
	private boolean running;
	private final long mpf = 20;
	
	public GameUpdater() {
		running = false;
		enemies = new ArrayList<BaseCreature>();
		projectiles = new ArrayList<>();
		at = new AffineTransform();
		lake = Assets.getAssets().getImage("images/Lake.png");
		BufferedImage ironcladImage = Assets.getAssets().getImage("images/Ironclad.png");
		
		ironclad = new Ironclad(300, 230, 70, 120);
		ironclad.setAnimation(new Animation(new BufferedImage[][]{{ironcladImage}}));
	}
	
	public void updateGame() {
		ironclad.update(enemies, ironclad, projectiles);
		
		for (int i=0; i<enemies.size(); i++) {
			BaseCreature bc = enemies.get(i);
			bc.getAnimation().update(mpf);
			bc.update(enemies, ironclad, projectiles);
		}
		
		for (int i=0; i<projectiles.size(); i++) {
			projectiles.get(i).update(enemies, ironclad, projectiles);
		}
		
		int mouseButton = GameWindow.getGameWindow().getMouseButton();
		if (mouseButton == MouseEvent.BUTTON1) {
			ironclad.fire(1, false, projectiles);
		} else if (mouseButton == MouseEvent.BUTTON2 || mouseButton == MouseEvent.BUTTON3) {
			ironclad.fire(1, true, projectiles);
		}	
	}
	
	public void drawGame() {
		Graphics2D g = (Graphics2D)GameWindow.getGameWindow().getDrawGraphics();
		g.drawImage(lake, 0, 0, null);
		
		//Testing
		g.setColor(Color.WHITE);
		g.fillRect(GameWindow.getGameWindow().getMousePositionX(), GameWindow.getGameWindow().getMousePositionY(), 10, 10);
		
		drawCreature(g, ironclad);
		for (int i=0; i<enemies.size(); i++) {
			drawCreature(g, enemies.get(i));
		}
		
		for (int i=0; i<projectiles.size(); i++) {
			g.setColor(projectiles.get(i).getColor());
			g.fillOval((int)projectiles.get(i).getX() - 2, 
						(int)projectiles.get(i).getY() - 2, 4, 4);
		}
		GameWindow.getGameWindow().swapBuffers(g);
	}
	
	private void drawCreature(Graphics2D g, BaseCreature bc) {
		BufferedImage image;
		at.setToIdentity();
		image = bc.getAnimation().getCurrentFrame();
		
		//  Rotate the image and then draw it  //
		g.translate(bc.getX(), bc.getY());
		g.rotate(bc.getAngle() + (Math.PI / 2));
		g.drawImage(image, -(image.getWidth()/2), -(image.getHeight()/2), null);
		g.rotate(-bc.getAngle() - (Math.PI / 2));
		g.translate(-bc.getX(), -bc.getY()); // move it back
		
		g.setColor(Color.MAGENTA);
		g.drawLine((int)bc.getX(), (int)bc.getY(), (int)bc.getX() + (int)(Math.cos(bc.getAngle()) * 20), 
									(int)bc.getY() + (int)(Math.sin(bc.getAngle()) * 20));
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
					System.out.println("Error in GameUpdater with sleeping");
				}
			}
		}
	}
}