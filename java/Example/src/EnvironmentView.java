import gui.SwingConsole;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class EnvironmentView extends JFrame {
	private static double scale = 0.8;
	private Environment env;
	private Agent agent;

	private int height;
	private int width;

	public EnvironmentView(Environment env) {
		height = Environment.HEIGHT;
		width = Environment.WIDTH;
		this.env = env;
		setLayout(new GridLayout(height, width));
		setAgent(new ModelBasedAgent2(env));
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				add(new Block(agent.row == i && agent.col == j, env.IsDirty(i,
						j)));
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

			if (isDirty) {
				g.setColor(Color.black);
				g.fillRect(0, 0, getSize().width, getSize().height);
			}
			if (hasAgent) {
				g.setColor(Color.red);
				g.fillOval((int) (getSize().width * (1 - scale) / 2),
						(int) (getSize().height * (1 - scale) / 2),
						(int) (getSize().width * scale),
						(int) (getSize().height * scale));
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
			Environment env = new Environment();
			Agent agent = new ModelBasedAgent2(env);
			SwingConsole.run(600, 600, EnvironmentView.class, env);
		}
	}
}
