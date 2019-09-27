package edu.frcc.csclub.cc.examples;

import java.awt.Color;

import edu.frcc.csclub.cc.CCApp;

/**
 * Fills the screen with a color pattern.
 * 
 * @author Erin M. Gunn
 *
 */
public class ColorPattern extends CCApp {
	private static final int DIM = 256;

	public ColorPattern() {
		super("Color Pattern", DIM, DIM);
	}

	@Override
	public void begin() {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint() {
		// TODO Auto-generated method stub
		for (int i = 0; i < DIM; ++i) {
			for (int j = 0; j < DIM; ++j) {
				Color c = colorFromInt(
					i % 256,
					j % 256,
					(i * j) % 256
				);

				getGfx().setColor(c);
				setPixel(i, j);
			}
		}
	}

	private void setPixel(int x, int y) {
		getGfx().drawLine(x, y, x, y);
	}
	
	public static void main(String[] args) {
		new ColorPattern();
	}
}
