package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SineWave extends JFrame {
	private SineDraw sines = new SineDraw();
	private JSlider adjustCycles = new JSlider(1, 4, 1);
	private JSlider adjustScaleFactor = new JSlider(4, 50, 20);

	public SineWave() {
		add(sines);
		adjustScaleFactor.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				sines.setScaleFactor(((JSlider) e.getSource()).getValue());
			}
		});
		adjustCycles.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				sines.setCycles(((JSlider) e.getSource()).getValue());
			}
		});
		add(BorderLayout.NORTH, adjustScaleFactor);
		add(BorderLayout.SOUTH, adjustCycles);
	}

	private static class SineDraw extends JPanel {
		private int scaleFactor;
		private int cycles;
		private int points;
		private double[] sines;
		private int[] pts;

		public SineDraw() {
			cycles = 1;
			scaleFactor = 20;
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			init();
			int maxWidth = getWidth();
			double hstep = (double) maxWidth / (double) points;
			int maxHeight = getHeight();
			pts = new int[points];
			for (int i = 0; i < points; i++)
				pts[i] = (int) (-sines[i] * maxHeight / 2 * .95 + maxHeight / 2);
			g.setColor(Color.RED);
			for (int i = 1; i < points; i++) {
				int x1 = (int) ((i - 1) * hstep);
				int x2 = (int) (i * hstep);
				int y1 = pts[i - 1];
				int y2 = pts[i];
				g.drawLine(x1, y1, x2, y2);
			}
		}

		public void setScaleFactor(int sf) {
			scaleFactor = sf;
			repaint();
		}

		private void init() {
			points = scaleFactor * cycles * 2;
			sines = new double[points];
			for (int i = 0; i < points; i++) {
				double radians = (Math.PI / scaleFactor) * i;
				sines[i] = Math.sin(radians);
			}
		}

		public void setCycles(int newCycles) {
			cycles = newCycles;
			repaint();
		}
	}

	public static void main(String[] args) {
		SwingConsole.run(SineWave.class, 400, 300);
	}
}
