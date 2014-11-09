package bropals.tag16_2.projectile;

import java.util.ArrayList;

public class Projectile {
	
	private float x, y, angle, speed;
	private boolean enemyProjectile; // is this an enemy projectile?
	
	public Projectile(float x, float y, float angle, float speed, boolean enemyProjectile) {
		this.x=x;
		this.y=y;
		this.angle=angle;
		this.speed=speed;
		this.enemyProjectile=enemyProjectile;
	}
	
	public void update(ArrayList<BaseCreature> enemies, BaseCreature ironclad, ArrayList<Projectile> projectiles) {
		this.x+=Math.cos(angle);
		this.y+=Math.sin(angle);
	}
	
}