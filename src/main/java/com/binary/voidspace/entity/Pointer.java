package com.binary.voidspace.entity;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.binary.voidspace.graphics.Sprite;

public class Pointer extends Entity {

	public Pointer(Sprite sprite) {
		this.sprite = sprite;
	}

	public void generatePosition() {
		x = Mouse.getX();
		y = Display.getHeight() - Mouse.getY();
	}

}
