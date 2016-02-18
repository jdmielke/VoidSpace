package com.binary.voidspace.level;

public class Level {
	protected int width;
	protected int height;
	protected int[] tilesInt;
	protected int[] tiles;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
	
	private void generateLevel() {
		
	}
	
	private void loadLevel(String path) {
		
	}
	
}
