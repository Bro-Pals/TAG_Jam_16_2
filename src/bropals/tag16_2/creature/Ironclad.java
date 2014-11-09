package bropals.tag16_2.creature;

import bropals.tag16_2.Animation;

/**
	The player's boat thing
*/
public class Ironclad extends BaseCreature {
	
	private final float CANNON_OFFSET = 30; // pixels
	private final float FIRE_DELAY = 75; // frames
	private final float BETWEEN_CANNONS_DELAY = 12; // frames
	
	private float secondCannonTimer, thirdCannonTimer, superCannonTimer;
	private boolean firingDirection = false; // variable for the direction the cannons are being fired
	
	public Ironclad(float x, float y, float w, float h) {
		super(x, y, w, h);
		secondCannonTimer = -1;
		thirdCannonTimer = -1;
		superCannonTimer = -1;
	}
	
	public void update() {
		super.update();
		
<<<<<<< HEAD
		Point mousePos = GameWindow.getGameWindow().getMousePosition();
		
		// get da vector of the mouse pos relative to the ironclad
		float diffX = (float)mousePos.getX() - getX();
		float diffY = (float)mousePos.getY() - getY();
		float mag = Math.sqrt((diffX * diffX) + (diffY * diffY));
		diffX = diffX/mag;
		diffY = diffY/mag;
		
		float dp = (Math.sin(getAngle()) * diffY) + (Math.cos(getAngle()) * diffX);
		
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
=======
>>>>>>> dd2f328313a0988577376bcfa86fb4743365887f
	}
	
	public void fire(int cannonNum, boolean left) {
		if (superCannonTimer > 0) {
			return; // can't fire because it's still on cooldown
		}
		// the direction the cannon is fired
		float angle = left ? getAngle() + (Math.PI/2) : getAngle() - (Math.PI/2);
		firingDirection = left;
		
		float startingPosX = getX() - (Math.cos(getAngle()) * CANNON_OFFSET);
		float startingPosY = getY() - (Math.sin(getAngle()) * CANNON_OFFSET);
		if (cannonNum == 2) {
			startingPosX = getX();
			startingPosY = getY();
		} else if(cannonNum == 3) {
			startingPosX = getX() + (Math.cos(getAngle()) * CANNON_OFFSET);
			startingPosY = getY() + (Math.sin(getAngle()) * CANNON_OFFSET);
		}	
	}
}

