package bropals.tag16_2.projectile;

public class Projectile {
	
	private float x, y, angle, speed;
	
	public Projectile(float x, float y, float angle, float speed) {
		this.x=x;
		this.y=y;
		this.angle=angle;
		this.speed=speed;
	}
	
	public void update() {
		this.x+=Math.cos(angle);
		this.y+=Math.sin(angle);
	}
	
}