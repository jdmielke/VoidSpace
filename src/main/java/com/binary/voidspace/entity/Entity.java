package com.binary.voidspace.entity;

import com.binary.voidspace.graphics.Sprite;

public abstract class Entity {
	public int x, y;
	private boolean removed = false;
	protected Sprite sprite;
	
	public void update() {
	}
	
	public void draw() {
		sprite.draw((int)x, (int)y);
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
}
