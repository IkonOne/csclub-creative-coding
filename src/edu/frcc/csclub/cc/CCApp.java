package edu.frcc.csclub.cc;

import java.awt.geom.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class CCApp {
	public double getDeltaTime() {
		return 1.0 / 50.0;
	}

	public Graphics2D getGfx() {
		return _gfx;
	}
	
	public Random getRandom() {
		return _random;
	}

	public CCApp(String title, int width, int height) {
		init(title, width, height);
	}

	public abstract void begin();
	public abstract void update();
	public abstract void paint();
	
	public Color colorFromInt(int r, int g, int b) {
		return colorFromInt(r, g, b, 255);
	}
	
	public Color colorFromInt(int r, int g, int b, int a) {
		return new Color(r / 256.0f, g / 256.0f, b / 256.0f, a / 256.0f);
	}
	
	@SuppressWarnings("serial")
	public static class CCPoint extends Point2D.Double {
		public int getXint() { return (int)Math.round(x); }
		public int getYint() { return (int)Math.round(y); }

		public CCPoint() { super(); } 
		public CCPoint(double x, double y) { super(x, y); }
	}
	
	@SuppressWarnings("serial")
	public static class CCColor extends java.awt.Color {
		public CCColor(float r, float g, float b) {
			super(r, g, b);
			// TODO Auto-generated constructor stub
		}
		
		public CCColor(float r, float g, float b, float a) {
			super(r, g, b, a);
		}
	}

	private void init(String title, int width, int height) {
		_random = new Random();

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(width, height));
		panel.setLayout(null);

		Canvas canvas = new Canvas();
		canvas.setIgnoreRepaint(true);
		panel.add(canvas);
		canvas.setBounds(0, 0, width, height);
		frame.pack();
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		BufferStrategy strat = canvas.getBufferStrategy();

		Timer timer = new Timer();
		timer.schedule(
			new TimerTask() {

				@Override
				public void run() {
					_gfx = (Graphics2D) strat.getDrawGraphics();
					if (_firstRun) {
						begin();
						_firstRun = false;
					}

					update();
					paint();
					_gfx = null;
					strat.show();
				}
				
			},
			1,
			20
		);
	}
	
	private boolean _firstRun = true;
	private Graphics2D _gfx;
	private Random _random;
}
