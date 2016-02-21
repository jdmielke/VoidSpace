package com.binary.voidspace.graphics;

import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {

	public static Sprite playerShip1 = new Sprite(SpriteSheet.playerShips, 32, 1, 0);
	public static Sprite pointer1 = new Sprite(SpriteSheet.pointers, 16, 0, 0);

	private static final int SCALE = 2;

	public Texture texture;
	int sheetWidth;
	int sheetHeight;
	int xCoord;
	int yCoord;
	int size;

	public Sprite(SpriteSheet sheet, int size, int x, int y) {
		texture = sheet.getTexture();
		this.sheetWidth = sheet.getTexture().getImageWidth();
		this.sheetHeight = sheet.getTexture().getImageHeight();
		this.size = size;
		this.xCoord = x;
		this.yCoord = y;
	}

	public void draw(int x, int y, float rotation) {
		// store the current model matrix
		glPushMatrix();

		// bind to the appropriate texture for this sprite
		texture.bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

		// translate to the right location and prepare to draw
		glTranslatef(x, y, 0);
		// rotate to point a direction
		glRotatef(rotation, 0.0f, 0.0f, 1.0f);
		// rotate around center
		glTranslatef(-(float) size / 2 * SCALE, -(float) size / 2 * SCALE, 0);
		// draw a quad textured to match the sprite
		glBegin(GL_QUADS);
		{
			// glTexCoord2f(0, (float)((yCoord + 1) * size) / sheetHeight);
			glTexCoord2f((float) (xCoord * size) / sheetWidth, (float) yCoord * size / sheetHeight);
			glVertex2f(0, 0);
			// glTexCoord2f((float)((xCoord + 1) * size) / sheetWidth,
			// (float)((yCoord + 1) * size) / sheetHeight);
			glTexCoord2f((float) ((xCoord + 1) * size) / sheetWidth, (float) yCoord * size
					/ sheetHeight);
			glVertex2f(size * SCALE, 0);
			// glTexCoord2f((float)((xCoord + 1) * size) / sheetWidth
			// ,(float)yCoord * size / sheetHeight);
			glTexCoord2f((float) ((xCoord + 1) * size) / sheetWidth, (float) ((yCoord + 1) * size)
					/ sheetHeight);
			glVertex2f(size * SCALE, size * SCALE);
			// glTexCoord2f(0, (float)yCoord * size / sheetHeight);
			glTexCoord2f((float) (xCoord * size) / sheetWidth, (float) ((yCoord + 1) * size)
					/ sheetHeight);
			glVertex2f(0, size * SCALE);
		}
		glEnd();

		// restore the model view matrix to prevent contamination
		glPopMatrix();
	}

	public int getSize() {
		return size;
	}
}
