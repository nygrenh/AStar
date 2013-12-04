package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class DrawingAreaKeyListener implements KeyListener {
	private DrawingArea drawingArea;

	public DrawingAreaKeyListener(DrawingArea drawingArea) {
		this.drawingArea =  drawingArea;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_V){
			drawingArea.toggleVisualization();
		}		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
