import gui.SwingConsole;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class EnvironmentView extends JFrame {
	private static double scale = 0.8;

	private int height;
	private int width;

	public EnvironmentView() {
		this(4, 4);
	}

	public EnvironmentView(int h, int w) {
		height = h;
		width = w;
		setLayout(new GridLayout(h, w));
		for(int i=0; i<h; ++i) {
			for(int j=0; j<w; ++j) {
				// TODO retrieve data from model
				add(new Block(true, true));
			}
		}
		setVisible(true);
	}

	private class Block extends JPanel {
		private boolean hasAgent;
		private boolean isDirty;

		public Block(boolean hasAgent, boolean isDirty) {
			this.hasAgent = hasAgent;
			this.isDirty = isDirty;
			setBorder(new LineBorder(Color.blue));
			repaint();
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			if(isDirty) {
				g.setColor(Color.black);
				// g.fillOval((int)(getSize().width*(1-scale)/2), (int)(getSize().height*(1-scale)/2),
				// 		(int)(getSize().width*scale), (int)(getSize().height*scale));
				g.fillRect(0, 0, getSize().width, getSize().height);
			}
			if(hasAgent) {
				g.setColor(Color.red);
				g.fillOval((int)(getSize().width*(1-scale)/2), (int)(getSize().height*(1-scale)/2),
						(int)(getSize().width*scale), (int)(getSize().height*scale));
			}
		}

		public void putAgent() {
			hasAgent = true;
			repaint();
		}
		public void removeAgent() {
			hasAgent = false;
			repaint();
		}
		public void clean() {
			isDirty = false;
			repaint();
		}
	}

	public static class Test {
		public static void main(String[] argv) {
			SwingConsole.run(EnvironmentView.class, 600, 600);
		}
	}
}
