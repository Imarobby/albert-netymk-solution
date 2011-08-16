package gui;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class DrawBall extends JFrame {
	public DrawBall() {
		super("Draw");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 400);
		setVisible(true);
		addMouseListener(new MouseAdapter() {
			private int r = 25;

			public void mousePressed(MouseEvent e) {
				Graphics g = getGraphics();
				g.fillOval(e.getX() - r, e.getY() - r, 2 * r, 2 * r);
			}
		});
	}

	public static void main(String[] args) {
		DrawBall t = new DrawBall();
	}
}
