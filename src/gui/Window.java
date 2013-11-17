package gui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import data_structures.Node;

public class Window implements Runnable{

	private JFrame frame;
	private Dimension dimension;
	
	@Override
	public void run() {
		frame = new JFrame("A");
		dimension = new Dimension(1000, 1000);
		frame.setPreferredSize(dimension);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		createComponents(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}
	
	private void createComponents(Container contentPane) {
		Node map[][] = new Node[100][100];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = new Node();
			}
		}
		DrawingArea drawingArea = new DrawingArea(map, dimension);
		contentPane.add(drawingArea);
		drawingArea.addMouseListener(new DrawingAreaMouseListener(drawingArea, map));
	}

	public static void main(String[] args) {
		new Window().run();
	}

}
