package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import algorithms.AStar;

import data_structures.Coordinates;
import data_structures.Node;
import data_structures.NodeType;

public class DrawingAreaMouseListener implements MouseListener {

	private DrawingArea area;
	private Node[][] map;
	private Coordinates lastClick;
	private AStar aStar;

	public DrawingAreaMouseListener(DrawingArea area, Node[][] map, AStar aStar) {
		this.area = area;
		this.map = map;
		this.aStar = aStar;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / area.width();
		int y = e.getY() / area.height();
		if (e.getButton() == MouseEvent.BUTTON1)
			area.setStart(x, y);
		if (e.getButton() == MouseEvent.BUTTON3) {
			area.setEnd(x, y);
		}
		update();
	}

	private void update() {
		if (area.start.x != -1 && area.end.x != -1) {
			Node start = map[area.start.x][area.start.y];
			Node end = map[area.end.x][area.end.y];
			area.setPath(aStar.findPath(start, end, map));
		}
		area.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX() / area.width();
		int y = e.getY() / area.height();
		lastClick = new Coordinates(x, y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX() / area.width();
		int y = e.getY() / area.height();
		if (x != lastClick.x || y != lastClick.y) {
			int i = lastClick.x - 1;
			while (i != x) {
				int j = lastClick.y - 1;
				while (j != y) {
					changeNodeMode(e, i + 1, j + 1);
					if (j < y)
						j++;
					else
						j--;
				}
				if (i < x)
					i++;
				else
					i--;
			}
		}
		update();
	}

	private void changeNodeMode(MouseEvent e, int i, int j) {
		try {
			if (e.getButton() == MouseEvent.BUTTON1) {
				map[i][j].setType(NodeType.wall);
			}
			if (e.getButton() == MouseEvent.BUTTON2) {
				map[i][j].setType(NodeType.sand);
			}
			if (e.getButton() == MouseEvent.BUTTON3) {
				map[i][j].setType(NodeType.normal);
			}
		} catch (ArrayIndexOutOfBoundsException e1) {
		}
	}

}
