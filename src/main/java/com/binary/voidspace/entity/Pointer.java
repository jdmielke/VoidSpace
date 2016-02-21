package com.binary.voidspace.entity;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.binary.voidspace.Game;
import com.binary.voidspace.graphics.Sprite;

public class Pointer extends Entity {

	public Pointer(Sprite sprite) {
		this.sprite = sprite;
	}

	public void generatePosition() {
		x = Mouse.getX() + Game.player.getX() - Game.width/2;
		y = Display.getHeight() - Mouse.getY() + Game.player.getY() - Game.height/2;
	}

}
