package bropals.tag16_2.creature;

import bropals.tag16_2.Animation;
import bropals.tag16_2.projectile.*;

/**
	The player's boat thing
*/
public class Ironclad extends BaseCreature {
	
	private final float CANNONBALL_SPEED = 5;
	private final float CANNON_OFFSET = 30; // pixels
	private final float FIRE_DELAY = 75; // frames
	private final float BETWEEN_CANNONS_DELAY = 12; // frames
	
	private float secondCannonTimer, thirdCannonTimer, superCannonTimer, speed, turnSpeed;
	private boolean firingDirection = false; // variable for the direction the cannons are being fired
	
	public Ironclad(float x, float y, float w, float h) {
		super(x, y, w, h);
		secondCannonTimer = -1;
		thirdCannonTimer = -1;
		superCannonTimer = -1;
		
		speed = 5;
		turnSpeed = Math.pi/16;
	}
	
	public void update() {
		super.update();
		
		Point mousePos = GameWindow.getGameWindow().getMousePosition();
		
		// get da vector of the mouse pos relative to the ironclad
		float diffX = (float)mousePos.getX() - getX();
		float diffY = (float)mousePos.getY() - getY();
		float mag = Math.sqrt((diffX * diffX) + (diffY * diffY));
		diffX = diffX/mag;
		diffY = diffY/mag;
		
		float dp = (Math.sin(getAngle() + (Math.PI/2)) * diffY) + 
			(Math.cos(getAngle() + (Math.PI/2)) * diffX); // transform if pi/2
		
		if (Math.abs(dp) < 0.2) {
			if (dp < 0) {
				setAngle(getAngle() + turnSpeed);
			} else {
				setAngle(getAngle() - turnSpeed);
			}
		}
		
		superCannonTimer--; // decrease the timer between firing

		if (secondCannonTimer > 0) {
			secondCannonTimer--;
			if (secondCannonTimer <= 0) {
				fire(2, firingDirection);
			}
		}
		
		if (thirdCannonTimer > 0) {
			thirdCannonTimer--;
			if (thirdCannonTimer <= 0) {
				fire(3, firingDirection);
			}
		}
	}
	
	public void fire(int cannonNum, boolean left) {
		if (superCannonTimer > 0) {
			return; // can't fire because it's still on cooldown
		}
		// the direction the cannon is fired
		float angle = left ? getAngle() + (Math.PI/2) : getAngle() - (Math.PI/2);
		firingDirection = left;
		
		// calculate the position of the cannonball
		float startingPosX = 0;
		float startingPosY = 0;
		// change the offset of the starting position depending on the numbers
		if (cannonNum == 1) {
			startingPosX = getX() - (Math.cos(getAngle()) * CANNON_OFFSET);
			startingPosY = getY() - (Math.sin(getAngle()) * CANNON_OFFSET);
		}else if (cannonNum == 2) {
			startingPosX = getX();
			startingPosY = getY();
		} else if(cannonNum == 3) {
			startingPosX = getX() + (Math.cos(getAngle()) * CANNON_OFFSET);
			startingPosY = getY() + (Math.sin(getAngle()) * CANNON_OFFSET);
		}
		
		startingPosX += Math.cos(angle) * 20;
		startingPosY += Math.sin(angle) * 20;
		
		
	}
}

