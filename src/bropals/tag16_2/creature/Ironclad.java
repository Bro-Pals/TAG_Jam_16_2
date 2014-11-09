package bropals.tag16_2.creature;

import bropals.tag16_2.Animation;
import bropals.tag16_2.projectile.*;
import java.util.ArrayList;
import java.awt.Point;
import bropals.tag16_2.GameWindow;

/**
	The player's boat thing
*/
public class Ironclad extends BaseCreature {
	
	private final float CANNONBALL_SPEED = 5;
	private final float CANNON_OFFSET = 30; // pixels
	private final float FIRE_DELAY = 75; // frames
	private final float BETWEEN_CANNONS_DELAY = 12; // frames
	private final float SPEED = 5;
	
	private float secondCannonTimer, thirdCannonTimer, superCannonTimer, speed, turnSpeed;
	private boolean firingDirection = false; // variable for the direction the cannons are being fired
	
	public Ironclad(float x, float y, float w, float h) {
		super(x, y, w, h, 100); // 100 health
		secondCannonTimer = -1;
		thirdCannonTimer = -1;
		superCannonTimer = -1;
		
		speed = SPEED;
		turnSpeed = (float)Math.PI/16;
	}
	
	public void update(ArrayList<BaseCreature> enemies, BaseCreature ironclad, ArrayList<Projectile> projectiles) {
		super.update(enemies, ironclad, projectiles);
		
		// get da vector of the mouse pos relative to the ironclad
		float diffX = (float)GameWindow.getGameWindow().getMousePositionX() - getX();
		float diffY = (float)GameWindow.getGameWindow().getMousePositionY() - getY();
		float mag = (float)Math.sqrt((diffX * diffX) + (diffY * diffY));
		diffX = diffX/mag;
		diffY = diffY/mag;
		
		// don't move when you're too close to the mouse
		if (mag > 20) {
			speed = SPEED;
		} else {
			speed = 0;
		}
		
		// dot product
		float dp = (float)(Math.sin(getAngle() + (Math.PI/2)) * diffY) + 
			(float)(Math.cos(getAngle() + (Math.PI/2)) * diffX); // transform if pi/2
		
		// turning the boat based on where the mouse is compared to the boat.
		if (Math.abs(dp) > 0.85) { // only turn if you need to keep turning
			if (dp < 0) {
				setAngle(getAngle() + turnSpeed);
			} else {
				setAngle(getAngle() - turnSpeed);
			}
		}
		
		superCannonTimer--; // decrease the timer between firing
		// fire the second cannon when the timer runs out
		if (secondCannonTimer > 0) {
			secondCannonTimer--;
			if (secondCannonTimer <= 0) {
				fire(2, firingDirection, projectiles);
			}
		}
		// fire the third cannon when the timer runs out
		if (thirdCannonTimer > 0) {
			thirdCannonTimer--;
			if (thirdCannonTimer <= 0) {
				fire(3, firingDirection, projectiles);
			}
		}
	}
	
	public void fire(int cannonNum, boolean left, ArrayList<Projectile> projectiles) {
		if (superCannonTimer > 0) {
			return; // can't fire because it's still on cooldown
		}
		// the direction the cannon is fired
		float angle = left ? getAngle() + (float)(Math.PI/2) : getAngle() - (float)(Math.PI/2);
		firingDirection = left;
		
		// calculate the position of the cannonball
		float startingPosX = 0;
		float startingPosY = 0;
		// change the offset of the starting position depending on the numbers
		if (cannonNum == 1) {
			startingPosX = getX() - (float)(Math.cos(getAngle()) * CANNON_OFFSET);
			startingPosY = getY() - (float)(Math.sin(getAngle()) * CANNON_OFFSET);
			secondCannonTimer = BETWEEN_CANNONS_DELAY;
			thirdCannonTimer = BETWEEN_CANNONS_DELAY * 2; // set the timers after the first cannon fires
		}else if (cannonNum == 2) {
			startingPosX = getX();
			startingPosY = getY();
		} else if(cannonNum == 3) {
			startingPosX = getX() + (float)(Math.cos(getAngle()) * CANNON_OFFSET);
			startingPosY = getY() + (float)(Math.sin(getAngle()) * CANNON_OFFSET);
		}
		
		startingPosX += Math.cos(angle) * 20;
		startingPosY += Math.sin(angle) * 20;
		
																						// not enemy, 2 damage
		projectiles.add(new Projectile(startingPosX, startingPosY, angle, CANNONBALL_SPEED, false, 2));
	}
}

