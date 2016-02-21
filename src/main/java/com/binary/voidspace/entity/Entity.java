package com.binary.voidspace.entity;

import com.binary.voidspace.graphics.Sprite;

public abstract class Entity {
	public int x, y;
	private boolean removed = false;
	protected Sprite sprite;
	protected float rotation;

	public void update() {
	}

	public void draw() {
		sprite.draw((int) x, (int) y, rotation);
	}

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getRotation() {
		return rotation;
	}

	public Sprite getSprite() {
		return sprite;
	}
}
