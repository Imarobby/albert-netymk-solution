package gui.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static gui.HorseRace.FINISH_LINE;

public class HorseView extends JPanel {
	private int strides;

	public void draw(int s) {
		strides = s;
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.red);
		g.drawLine(0, getHeight()/2,
				// getWidth()*strides/FINISH_LINE, getHeight()/2);
				strides, getHeight()/2);
	}
}
