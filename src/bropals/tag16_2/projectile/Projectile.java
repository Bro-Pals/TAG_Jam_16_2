package bropals.tag16_2.projectile;

import java.util.ArrayList;
import bropals.tag16_2.projectile.*;
import bropals.tag16_2.creature.*;

public class Projectile {
	
	private float x, y, angle, speed;
	private int damage;
	private boolean enemyProjectile; // is this an enemy projectile?
	
	public Projectile(float x, float y, float angle, float speed, boolean enemyProjectile, int damage) {
		this.x=x;
		this.y=y;
		this.angle=angle;
		this.speed=speed;
		this.enemyProjectile=enemyProjectile;
		this.damage=damage;
	}
	
	public void update(ArrayList<BaseCreature> enemies, BaseCreature ironclad, ArrayList<Projectile> projectiles) {
		this.x+=(this.speed*Math.cos(angle));
		this.y+=(this.speed*Math.sin(angle));
		if (enemyProjectile) {
			if (ironclad.pointCollidesWith(this.x, this.y)) {
				//Damage the iron clad
				
			}
		} else {
			
		}
	}
	
}