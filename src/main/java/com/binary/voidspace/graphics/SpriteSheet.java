package com.binary.voidspace.graphics;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.binary.voidspace.Game;

public class SpriteSheet {

	private Texture texture;
	
	public static SpriteSheet playerShips = new SpriteSheet("playerShips.png");
	
	public SpriteSheet(String path) {
		try {
			this.texture = TextureLoader.getTexture(Game.TEXTURE_FORMAT_PNG, ResourceLoader.getResourceAsStream("/resources/textures/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Texture getTexture() {
		return texture;
	}

}
