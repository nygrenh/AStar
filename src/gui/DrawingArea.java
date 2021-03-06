package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;


import algorithms.AStar;

import data_structures.AStarNode;
import data_structures.Coordinates;
import data_structures.Node;
import data_structures.NodeType;

public class DrawingArea extends JPanel {

	private boolean visualization;

	private Node[][] map;
	private int width, height;
	Coordinates start, end;
	private List<Node> path;
	private AStar aStar;

	public DrawingArea(Node[][] map, Dimension size, AStar aStar) {
		this.map = map;
		this.width = (int) (size.getWidth() / map[0].length);
		this.height = (int) (size.getHeight() / map.length);
		start = new Coordinates(-1, -1);
		end = new Coordinates(-1, -1);
		this.aStar = aStar;
		this.visualization = false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		turnAntiAliasingOn(g);
		paintGrid(g);
		if (path != null) {
			paintPath(g);
		}
		paintStart(g);
		paintEnd(g);
	}

	private void paintPath(Graphics g) {
		g.setColor(Color.CYAN);
		for (int i = 0; i < path.size(); i++) {
			Coordinates coordinates = path.get(i).getCoordinates();
			int x = coordinates.x * width;
			int y = coordinates.y * height;
			g.fillRect(x + width * 1/4, y + height * 1/4, width * 2/4, height * 2/4);
		}

	}

	private void turnAntiAliasingOn(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
				g.setColor(Color.WHITE);
				if (map[i][j].getType() == NodeType.wall)
					g.setColor(Color.LIGHT_GRAY);
				if(map[i][j].getType() == NodeType.sand){
					g.setColor(Color.YELLOW);
				}
				if(map[i][j].getType() == NodeType.mud){
					g.setColor(Color.PINK);
				}
				AStarNode helpNode = aStar.getHelpNode(map[i][j]);
				if (helpNode != null) {
					if (helpNode.isEvaluated() && visualization)
						g.setColor(Color.BLUE);
					else if (helpNode.isInHeap() && visualization)
						g.setColor(Color.MAGENTA);
				}
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

	public void setPath(List<Node> path) {
		this.path = path;
	}
	
	public void toggleVisualization(){
		visualization = !visualization;
		this.repaint();
	}
}
