package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import data_structures.Coordinates;
import data_structures.Node;

public class DrawingArea extends JPanel {

	private Node[][] map;
	private int width, height;
	Coordinates start, end;

	public DrawingArea(Node[][] map, Dimension size) {
		this.map = map;
		this.width = (int) (size.getWidth() / map[0].length);
		this.height = (int) (size.getHeight() / map.length);
		start = new Coordinates(-1, -1);
		end = new Coordinates(-1, -1);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		turnAntiAliasingOn(g);
		paintGrid(g);
		paintStart(g);
		paintEnd(g);
	}

	private void turnAntiAliasingOn(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	}

	private void paintStart(Graphics g) {
		g.setColor(Color.RED);
		double ovalWidth = width - 1, ovalHeight = height - 1;
		int startX = (int) (start.x * width);
		int startY = (int) (start.y * height);
		g.fillOval(startX, startY, (int) ovalWidth, (int) ovalHeight);
	}

	private void paintEnd(Graphics g) {
		g.setColor(Color.GREEN);
		double ovalWidth = width - 1, ovalHeight = height - 1;
		int startX = (int) (end.x * width);
		int startY = (int) (end.y * height);
		g.fillOval(startX, startY, (int) ovalWidth, (int) ovalHeight);
	}

	private void paintGrid(Graphics g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				int x = i * width;
				int y = j * height;
				if (!map[i][j].blocked())
					g.setColor(Color.WHITE);
				else
					g.setColor(Color.LIGHT_GRAY);
				g.fillRect(x, y, width - 1, height - 1);
			}
		}
	}

	public void setStart(int x, int y) {
		start.x = x;
		start.y = y;
		repaint();
	}

	public void setEnd(int x, int y) {
		end.x = x;
		end.y = y;
		repaint();
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

}
