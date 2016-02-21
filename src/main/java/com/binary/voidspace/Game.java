package com.binary.voidspace;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glViewport;

import java.awt.Font;
import java.io.IOException;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.binary.voidspace.entity.Pointer;
import com.binary.voidspace.entity.mob.Player;
import com.binary.voidspace.graphics.Sprite;

public class Game implements Runnable {

	private final static String WINDOW_TITLE = "VoidSpace";
	public final static String TEXTURE_FORMAT_PNG = "PNG";

	private boolean running = false;

	private Thread gameThread;

	private TrueTypeFont font;

	public long lastFrame;
	private long lastFpsTime;
	private int fps;

	private Texture background;

	private int width = 800;
	private int height = 600;

	private Player player;
	public static Pointer pointer;

	public Game() {

	}

	public synchronized void start() {
		running = true;
		gameThread = new Thread(this, "Display");
		gameThread.start();

		// TODO add server options here

	}

	public void run() {
		initialize();

		while (running && !Display.isCloseRequested()) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			renderFrame();
			Display.update();
		}
		stop();
	}

	public void renderFrame() {
		Display.setVSyncEnabled(true);

		float delta = getDelta();
		lastFpsTime += delta;
		fps++;

		if (lastFpsTime >= 1000) {
			Display.setTitle(WINDOW_TITLE + " | FPS: " + fps);
			lastFpsTime = 0;
			fps = 0;
		}

		drawBackground();
		drawPlayer(delta);
		drawPointer();
		debugInfo();

	}

	private void drawPointer() {
		pointer.generatePosition();
		pointer.draw();
	}

	private void drawPlayer(float delta) {
		player.generatePosition();
		player.move(delta / 1000);
		player.draw();
		player.generateAngle();
	}

	private void drawBackground() {
		Color.white.bind();
		background.bind();
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f((float) background.getImageWidth() / background.getTextureWidth(), 0);
		glVertex2f(width, 0);
		glTexCoord2f((float) background.getImageWidth() / background.getTextureWidth(),
				(float) background.getImageHeight() / background.getTextureHeight());
		glVertex2f(width, height);
		glTexCoord2f(0, (float) background.getImageHeight() / background.getTextureHeight());
		glVertex2f(0, height);
		glEnd();
	}

	public synchronized void stop() {
		running = false;
		Display.destroy();
		System.exit(0);
	}

	public void initialize() {
		try {
			setDisplayMode();
			Display.setTitle(WINDOW_TITLE);
			Display.setFullscreen(false);
			Display.create();

			glEnable(GL_TEXTURE_2D);
			glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glDisable(GL_DEPTH_TEST);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, width, height, 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			glViewport(0, 0, width, height);

			player = new Player(Sprite.playerShip1, width / 2, height / 2);
			pointer = new Pointer(Sprite.pointer1);

			// makes mouse invisible for later
			Mouse.setNativeCursor(new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null));

			Font awtFont = new Font("Times New Roman", Font.PLAIN, 12); 
			font = new TrueTypeFont(awtFont, true); 
		} catch (LWJGLException le) {
			System.out.println("Game exiting - exception in initialization:");
			le.printStackTrace();
			running = false;
			return;
		}

		try {
			background = TextureLoader.getTexture(TEXTURE_FORMAT_PNG,
					ResourceLoader.getResourceAsStream("textures/emptySpaceBackground.png"));
			System.out.println("Texture loaded: " + background);
			System.out.println(">> Image width: " + background.getImageWidth());
			System.out.println(">> Image height: " + background.getImageHeight());
			System.out.println(">> Texture width: " + background.getTextureWidth());
			System.out.println(">> Texture height: " + background.getTextureHeight());
			System.out.println(">> Texture ID: " + background.getTextureID());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean setDisplayMode() {
		try {
			DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(width, height, -1,
					-1, -1, -1, 60, 60);

			org.lwjgl.util.Display.setDisplayMode(dm, new String[] { "width=" + width,
					"height=" + height, "freq=" + 60,
					"bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel() });
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to enter fullscreen, continuing in windowed mode");
		}

		return false;
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		return delta;
	}

	private void debugInfo() {
		font.drawString(600, 500, "ship x: " + player.getX() + " y: " + player.getY(), Color.yellow);
		font.drawString(600, 480, "cursor x: " + pointer.getX() + " y: " + pointer.getY(),
				Color.yellow);
		font.drawString(600, 460, "angle: " + player.getRotation(), Color.yellow);
	}

	public static void main(String[] args) {
		new Game().start();
	}
}
