package gui;

import java.lang.reflect.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {
	private JTextField
		rows = new JTextField("3"),
			 cols = new JTextField("3");
	private enum State { BLANK, XX, OO }
	private static class ToeDialog extends JDialog {
		private State turn = State.XX; // Start with x’s turn
		ToeDialog(int cellsWide, int cellsHigh) {
			setTitle("The game itself");
			setLayout(new GridLayout(cellsWide, cellsHigh));
			for(int i = 0; i < cellsWide * cellsHigh; i++)
				add(new ToeButton());
			setSize(cellsWide * 200, cellsHigh * 200);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}

		private class ToeButton extends JPanel {
			private State state = State.BLANK;
			public ToeButton() {
				addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						if(state == State.BLANK) {
							state = turn;
							turn =
					(turn == State.XX ? State.OO : State.XX);
				repaint();
						}
					}
				});
			}
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				int
					x1 = 0, y1 = 0,
					   x2 = getSize().width - 1,
					   y2 = getSize().height - 1;
				g.drawRect(x1, y1, x2, y2);
				x1 = x2/4;
				y1 = y2/4;
				int wide = x2/2, high = y2/2;
				if(state == State.XX) {
					g.drawLine(x1, y1, x1 + wide, y1 + high);
					g.drawLine(x1, y1 + high, x1 + wide, y1);
				}
				if(state == State.OO)
					g.drawOval(x1, y1, x1 + wide/2, y1 + high/2);
			}
		}
	}
	public TicTacToe() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,2));
		p.add(new JLabel("Rows", JLabel.CENTER));
		p.add(rows);
		p.add(new JLabel("Columns", JLabel.CENTER));
		p.add(cols);
		add(p, BorderLayout.NORTH);
		JButton b = new JButton("go");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog d = new ToeDialog(
					new Integer(rows.getText()),
					new Integer(cols.getText()));
				d.setVisible(true);
			}
		});
		add(b, BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		SwingConsole.run(TicTacToe.class, 200, 200);
	}
}
