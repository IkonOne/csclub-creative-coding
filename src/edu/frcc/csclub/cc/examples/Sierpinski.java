package edu.frcc.csclub.cc.examples;

import java.awt.Color;
import java.awt.Graphics2D;

import edu.frcc.csclub.cc.CCApp;

/**
 * Draws Sierpinski's Triangle.
 * 
 * Rendering Sierpinski's Triangle is generally done using recursion.
 * This example renders the triangle at various depths (up to MAX_DEPTH)
 * rendering each depth for FRAMES_PER_DEPTH number of frames.
 * 
 * (The simulation runs at 40 frames per second)
 * 
 * @author Erin M. Gunn
 */
public class Sierpinski extends CCApp {
	private static final int MAX_DEPTH = 10;
	private static final int FRAMES_PER_DEPTH = 30;
	
	private static int _countDirection = 1;
	private static int _frameCount = 0;
	private static int _depth = 0;

	public Sierpinski() {
		super("Sierpinski's Triangle", 800, 800);
	}

	@Override
	public void begin() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		if (_frameCount == FRAMES_PER_DEPTH) {
			_frameCount = 0;
			_depth += _countDirection;
			
			if (_depth == 0 || _depth == MAX_DEPTH)
				_countDirection = -_countDirection;
		}
		
		_frameCount++;
	}

	@Override
	public void paint() {
		getGfx().setColor(Color.WHITE);
		getGfx().fillRect(0, 0, 800, 800);

		getGfx().setColor(Color.BLACK);
		getGfx().drawString("Depth: " + Integer.toString(_depth), 50, 50);

		displayTriangle(
			getGfx(),
			new int[] {
				400,
				0,
				800
			},
			new int[] {
				0,
				800,
				800
			},
			_depth
		);
	}
	
	private void displayTriangle(Graphics2D gfx, int[] xPoints, int[] yPoints, int depth) {
		gfx.drawPolygon(xPoints, yPoints, 3);
		
		if (depth > 0) {
			displayTriangle(
				gfx,
				new int[] {
					xPoints[0],
					(xPoints[0] + xPoints[1]) / 2,
					(xPoints[0] + xPoints[2]) / 2,
				},
				new int[] {
					yPoints[0],
					(yPoints[0] + yPoints[1]) / 2,
					(yPoints[0] + yPoints[2]) / 2,
				},
				depth - 1
			);
			
			displayTriangle(
				gfx,
				new int[] {
					xPoints[1],
					(xPoints[1] + xPoints[0]) / 2,
					(xPoints[1] + xPoints[2]) / 2
				},
				new int[] {
					yPoints[1],
					(yPoints[1] + yPoints[0]) / 2,
					(yPoints[1] + yPoints[2]) / 2
				},
				depth - 1
			);
			
			displayTriangle(
				gfx,
				new int[] {
					xPoints[2],
					(xPoints[2] + xPoints[0]) / 2,
					(xPoints[2] + xPoints[1]) / 2
				},
				new int[] {
					yPoints[2],
					(yPoints[2] + yPoints[0]) / 2,
					(yPoints[2] + yPoints[1]) / 2
				},
				depth - 1
			);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Sierpinski();
	}

}
