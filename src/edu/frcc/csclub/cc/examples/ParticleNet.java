package edu.frcc.csclub.cc.examples;

import java.awt.BasicStroke;
import java.awt.Color;

import edu.frcc.csclub.cc.CCApp;

/**
 * Draw's random, bouncing particles with lines fading in and out between them.
 * 
 * @author Erin M. Gunn
 */
public class ParticleNet extends CCApp {
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	private static final int NUM_POINTS = 50;
	private static final int LINE_DIST = 125;
	
	private CCPoint[] _points;
	private CCPoint[] _velocities;

	public ParticleNet() {
		super("Partile Net", WIDTH, HEIGHT);
		
	}
	
	@Override
	public void begin() {

		// create random points and velocities.
		_points = new CCPoint[NUM_POINTS];
		_velocities = new CCPoint[NUM_POINTS];
		
		// O(n)
		for (int i = 0; i < NUM_POINTS; ++i) {
			// Each point is initialized to a random location on the screen.
			_points[i] = new CCPoint(
				Math.random() * WIDTH,
				Math.random() * HEIGHT
			);
			
			// Each velocity is initialized to a random value in the range [+-2, +-2]
			_velocities[i] = new CCPoint(
				Math.random() * 4 - 2,
				Math.random() * 4 - 2
			);
		}

		getGfx().setColor(new Color(33, 33, 33));
		getGfx().fillRect(0, 0, WIDTH, HEIGHT);
	}

	@Override
	public void update() {
		// O(n)
		for (int i = 0; i < NUM_POINTS; ++i) {
			// move each particle at its velocity
			_points[i].x += _velocities[i].x;
			_points[i].y += _velocities[i].y;
			
			// if the particle hits the left or right edge of the screen, bounce off
			if (_points[i].x > WIDTH|| _points[i].x < 0)
				_velocities[i].x = -_velocities[i].x;
			
			// if the particle hits the top or bottom edge of the screen, bounce off
			if (_points[i].y > HEIGHT|| _points[i].y < 0)
				_velocities[i].y = -_velocities[i].y;
		}
	}

	@Override
	public void paint() {
		getGfx().setColor(new Color(33, 33, 33, 100));
		getGfx().fillRect(0, 0, WIDTH, HEIGHT);

		// O(n^2)
		// could be improved using a kd-tree or quadtree or grid
		for (CCPoint p1 : _points) {
			// draw each particle
			getGfx().setColor(new Color(255, 23, 68));
			getGfx().fillOval(p1.getXint() - 5, p1.getYint() - 5, 10, 10);

			// for each point
			getGfx().setStroke(new BasicStroke(2));
			for (CCPoint p2 : _points) {
				if (p1 == p2) continue;

				double dist = CCPoint.distance(p1.x, p1.y, p2.x, p2.y);
				if (dist < LINE_DIST) {
					getGfx().setColor(new Color(255, 23, 68, (int)(255 * (1 - dist / LINE_DIST))));
					getGfx().drawLine(p1.getXint(), p1.getYint(), p2.getXint(), p2.getYint());
				}
			}
		}
	}

	public static void main(String[] args) {
		new ParticleNet();
	}
}
