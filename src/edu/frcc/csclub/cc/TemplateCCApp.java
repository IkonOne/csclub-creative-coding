package edu.frcc.csclub.cc;

import java.awt.Color;

public class TemplateCCApp extends CCApp {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;

	public TemplateCCApp() {
		super("Template CCApp", WIDTH, HEIGHT);
		// WARNING: Do not call getGfx() here.  It will break.
		// All of your initialization code should go in the begin method.
	}

	@Override
	public void begin() {
		// TODO Initialize your application here.

	}

	@Override
	public void update() {
		// TODO Update your application here.

	}

	@Override
	public void paint() {
		// TODO Draw your application here.
		// e.g. 
		getGfx().setColor(Color.blue);
		getGfx().fillRect(0, 0, WIDTH, HEIGHT);
		
		int x = getRandom().nextInt(WIDTH);
		int y = getRandom().nextInt(HEIGHT);
		getGfx().setColor(Color.GREEN);
		getGfx().fillOval(x, y, 10, 10);
	}

	public static void main(String[] args) {
		// TODO Start your application by creating it.
		new TemplateCCApp();
	}

}
