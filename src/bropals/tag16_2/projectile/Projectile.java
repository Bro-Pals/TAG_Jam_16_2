package bropals.tag16_2.projectile;

import java.util.ArrayList;
import bropals.tag16_2.projectile.*;
import bropals.tag16_2.creature.*;
import java.awt.Color;

public class Projectile {
	
	private float x, y, angle, speed;
	private int damage;
	private boolean enemyProjectile; // is this an enemy projectile?
	private Color color;
	
	public Projectile(float x, float y, float angle, float speed, boolean enemyProjectile, int damage) {
		this.x=x;
		this.y=y;
		this.angle=angle;
		this.speed=speed;
		this.enemyProjectile=enemyProjectile;
		this.damage=damage;
		this.color = Color.BLACK;
	}
	
	public void update(ArrayList<BaseCreature> enemies, BaseCreature ironclad, ArrayList<Projectile> projectiles) {
		this.x+=(this.speed*Math.cos(angle));
		this.y+=(this.speed*Math.sin(angle));
		if (enemyProjectile) {
			if (ironclad.pointCollidesWith(this.x, this.y)) {
				//Damage the iron clad
				ironclad.damage(this.damage);
				projectiles.remove(this);
			}
		} else {
			
		}
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		this.color=c;
	}
	
	public float getX() { return x; }
	public float getY() { return y; }
}