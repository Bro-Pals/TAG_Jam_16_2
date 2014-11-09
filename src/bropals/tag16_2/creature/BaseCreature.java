package bropals.tag16_2.creature;

import bropals.tag16_2.Animation;
import java.util.ArrayList;
import bropals.tag16_2.projectile.*;

/**
	The basic enemy skeleton
*/
public abstract class BaseCreature {
	
	private float x, y, w, h, angle, speed;
	private Animation animation;
	
	public BaseCreature(float x, float y, float w, float h) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.angle = 0; // start at angle 0
		this.speed = 0;
	}
	
	public void update(ArrayList<BaseCreature> enemies, BaseCreature ironclad, ArrayList<Projectile> projectiles) {
		this.x += Math.cos(this.angle);
		this.y += Math.sin(this.angle);
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
	public float getWidth() { return w; }
	public float getHeight() { return h; }
	public float getSpeed() { return speed; }
	
	public void setX(float x) { this.x=x; }
	public void setY(float y) { this.y=y; }
	public void setWidth(float w) { this.w=w; }
	public void setHeight(float h) { this.h=h; }
	public void setSpeed(float s) { this.speed=s; }
	
	/**
		return the direction the player is facing in radians
		@return the player's direction in radians
	*/
	public float getAngle() {
		return angle;
	}
	
	public void setAngle(float a) {
		angle = a;
	}
	
	public Animation getAnimation() {
		return animation;
	}
	
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
}

