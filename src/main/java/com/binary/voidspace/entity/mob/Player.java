package com.binary.voidspace.entity.mob;

import org.lwjgl.input.Keyboard;

import com.binary.voidspace.graphics.Sprite;

public class Player extends Mob{

	protected String username;
	
	public Player(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	
	public void generatePosition() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			dy += 50;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			dy -= 50;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			dx -= 50;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			dx += 50;
		} 
	}
	
}
