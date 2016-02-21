package com.binary.voidspace.entity;

import com.binary.voidspace.Game;
import com.binary.voidspace.graphics.Sprite;

public class IcarusPrimaryWeapon extends Entity{
	private float range;
	private float speed;
	private static long fireRate;
	private static long lastFireTime;
	private long startTime;
	/** speed x direction */
	protected double dx;
	/** speed y direction */
	protected double dy;
	
	public IcarusPrimaryWeapon(float speed, float range, long fireRate) {
		this.sprite = Sprite.icarusPrimaryFire;
		this.x = Game.player.getX();
		this.y = Game.player.getY();
		this.speed = speed;
		this.range = range;
		this.rotation = Game.player.getRotation();
		IcarusPrimaryWeapon.fireRate = fireRate;
		dx = Math.sin(rotation * 0.01745329251f) * speed;
		dy = Math.cos(rotation * 0.01745329251f) * speed;
		startTime = Game.getTime();
		IcarusPrimaryWeapon.lastFireTime = startTime;
	}
	
	public void move(long delta) {
		if(speed * deltaTime() < range) {
			x += dx * delta;
			y -= dy * delta;
		} else {
			removed = true;
		}
	}
	
	private long deltaTime() {
		return Game.getTime() - startTime;
	}
	
	public static boolean canFire() {
		System.out.println(Game.getTime() - IcarusPrimaryWeapon.lastFireTime);
		if(Game.getTime() - IcarusPrimaryWeapon.lastFireTime > fireRate) {
			return true;
		} else {
			return false;
		}
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
}
