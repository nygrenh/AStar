package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import data_structures.Coordinates;
import data_structures.Node;

public class DrawingAreaMouseListener implements MouseListener {

	private DrawingArea area;
	private Node[][] map;
	private Coordinates lastClick;

	public DrawingAreaMouseListener(DrawingArea area, Node[][] map) {
		this.area = area;
		this.map = map;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / area.width();
		int y = e.getY() / area.height();
		if (e.getButton() == MouseEvent.BUTTON1)
			area.setStart(x, y);
		if (e.getButton() == MouseEvent.BUTTON3)
			area.setEnd(x, y);
		if (e.getButton() == MouseEvent.BUTTON2) {
			map[x][y].toggleBlocked();
			area.repaint();
		}
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
				int j = lastClick.y -1;
				while (j != y) {
					changeNodeMode(e, i+1, j+1);
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
		area.repaint();
	}

	private void changeNodeMode(MouseEvent e, int i, int j) {
		try {
			if (e.getButton() == MouseEvent.BUTTON1
					&& e.isShiftDown())
				map[i][j].toggleBlocked();
			else if (e.getButton() == MouseEvent.BUTTON1) {
				map[i][j].setBlocked();
			}
			if (e.getButton() == MouseEvent.BUTTON3) {
				map[i][j].setUnblocked();
			}
		} catch (ArrayIndexOutOfBoundsException e1) {
		}
	}

}
