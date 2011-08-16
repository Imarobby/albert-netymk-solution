package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.peer.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

public class SimpleWhiteBoard extends JFrame {
	private Image img;
	protected int lastX, lastY;

	protected void record(int x, int y) {
		lastX = x;
		lastY = y;
	}

	public SimpleWhiteBoard() {
		getContentPane().setBackground(Color.black);
		getContentPane().setForeground(Color.blue);
		getContentPane().setFont(new Font("sansserif", Font.PLAIN, 25));
		// setForeground(Color.blue);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				record(e.getX(), e.getY());
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				System.out.println(getContentPane().getClass().getName());
				getContentPane().getGraphics().drawLine(lastX, lastY, e.getX(), e.getY());
				record(e.getX(), e.getY());
			}
		});
		setVisible(true);
		System.out.println(getContentPane().getGraphics());
	}

	public static void main(String[] args) {
		SwingConsole.run(SimpleWhiteBoard.class, 400, 400);
	}
}
