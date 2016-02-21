package com.binary.voidspace.entity.mob;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.binary.voidspace.Game;
import com.binary.voidspace.entity.IcarusPrimaryWeapon;
import com.binary.voidspace.graphics.Sprite;

public class Player extends Mob {

	protected String username;
	protected int acceleration = 20;
	public static List<IcarusPrimaryWeapon> shotsFired;

	public Player(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		if(sprite == Sprite.icarus) {
			shotsFired = new ArrayList<IcarusPrimaryWeapon>();
		}
	}

	public void generatePosition() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			dy -= acceleration;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			dy += acceleration;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			dx -= acceleration;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			dx += acceleration;
		}
	}

	public void generateAngle() {
		float centerX = x;
		float centerY = y;
		float centerPointerX = Game.pointer.getX();
		float centerPointerY = Game.pointer.getY();

		rotation = (float) (Math.atan2((centerY - centerPointerY), (centerX - centerPointerX) * -1) * -57.2957795f) + 90;
	}

}
