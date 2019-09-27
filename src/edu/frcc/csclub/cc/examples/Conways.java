package edu.frcc.csclub.cc.examples;

import java.awt.Color;

import edu.frcc.csclub.cc.CCApp;

/**
 * Conways game of life.
 * 
 * This implementation uses a double buffered strategy.
 * The rules are calculated using the frontBuffer
 * writing the results to the backBuffer.
 * 
 * Rules to calculate which cells live or die: http://www.conwaylife.com/wiki/Conway%27s_Game_of_Life
 * 
 * Interesting patterns that can emerge: http://www.conwaylife.com/wiki/Main_Page
 * 
 * @author Erin M. Gunn
 */
public class Conways extends CCApp {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	private static final int CELL_DIM = 4;
	private static final int UPDATE_EVERY_X_FRAMES = 2;
	
	private static final int COLS = WIDTH / CELL_DIM;
	private static final int ROWS = HEIGHT / CELL_DIM;

	public static void main(String[] args) {
		new Conways();
	}
	
	private int frameCount;
	private boolean[] frontBuffer;
	private boolean[] backBuffer;
	
	public Conways() {
		super("Conway's Game of Life.", WIDTH, HEIGHT);
	}
	
	@Override
	public void begin() {
		frameCount = 0;
		frontBuffer = new boolean[COLS * ROWS];
		backBuffer = new boolean[COLS * ROWS];
		
		for (int i = 0; i < frontBuffer.length; ++i) {
			frontBuffer[i] = Math.random() > 0.5;
		}

		getGfx().setColor(new Color(33, 33, 33));
		getGfx().fillRect(0, 0, WIDTH, WIDTH);
	}

	@Override
	public void update() {
		if (frameCount % UPDATE_EVERY_X_FRAMES == 0) {
			stepSimulation();
			swapBuffers();
		}
		frameCount++;
	}

	@Override
	public void paint() {
		getGfx().setColor(new Color(33, 33, 33));
		getGfx().fillRect(0, 0, WIDTH, HEIGHT);
		getGfx().setColor(new Color(255, 23, 68));
		paintBuffer(frontBuffer);
	}
	
	private void stepSimulation() {
		boolean isAlive = false;
		int count = 0;
		for (int col = 0; col < COLS; ++col) {
			for (int row = 0; row < ROWS; ++row) {
				count = countNeighbors(frontBuffer, col, row);
				isAlive = getIsAlive(frontBuffer, col, row);
				if (isAlive) {
					setIsAlive(backBuffer, col, row, count == 2 || count == 3);
				}
				else {
					setIsAlive(backBuffer, col, row, count == 3);
				}
			}
		}
	}
	
	private void paintBuffer(boolean[] buffer) {
		for (int col = 0; col < COLS; ++col) {
			for (int row = 0; row < ROWS; ++row) {
				if (getIsAlive(buffer, col, row)) {
					getGfx().fillRect(col * CELL_DIM, row * CELL_DIM, CELL_DIM - 1, CELL_DIM - 1);
				}
			}
		}
	}
	
	private boolean getIsAlive(boolean[] buffer, int col, int row) {
		row = wrapRow(row);
		col = wrapCol(col);
		return buffer[row * COLS + col];
	}
	
	private void setIsAlive(boolean[] buffer, int col, int row, boolean isAlive) {
		row = wrapRow(row);
		col = wrapCol(col);
		buffer[row * COLS + col] = isAlive;
	}
	
	private int countNeighbors(boolean[] buffer, int col, int row) {
		row = wrapRow(row);
		col = wrapCol(col);
		int count = 0;
		
		for (int c = col - 1; c <= col + 1; ++c) {
			for (int r = row - 1; r <= row + 1; ++r) {
				if (c == col && r == row)
					continue;
				
				if (getIsAlive(buffer, c, r))
					count++;
			}
		}
		
		return count;
	}
	
	private int wrapRow(int row) {
		while (row < 0) row += ROWS;
		while (row >= ROWS) row -= ROWS;
		return row;
	}
	
	private int wrapCol(int col) {
		while (col < 0) col += COLS;
		while (col >= COLS) col -= COLS;
		return col;
	}
	
	private void swapBuffers() {
		boolean[] temp = frontBuffer;
		frontBuffer = backBuffer;
		backBuffer = temp;
	}
}