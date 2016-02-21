package com.binary.voidspace.entity.mob;

import com.binary.voidspace.entity.Entity;

public abstract class Mob extends Entity {
	
	/**speed x direction*/
	protected float dx;
	/**speed y direction*/
	protected float dy;
	
	public void move(float delta) {
		x += dx * delta;
		y += dy * delta;
	}
	
	public void update() {
	}
	
	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}
}
