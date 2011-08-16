package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CheckBoxes extends JFrame {
	private JTextArea t = new JTextArea(6, 15);
	private JCheckBox cb1 = new JCheckBox("Check Box 1"), cb2 = new JCheckBox(
			"Check Box 2"), cb3 = new JCheckBox("Check Box 3");

	public CheckBoxes() {
		cb1.addActionListener(toggleListener);
		cb2.addActionListener(toggleListener);
		cb3.addActionListener(toggleListener);
		setLayout(new FlowLayout());
		add(new JScrollPane(t));
		add(cb1);
		add(cb2);
		add(cb3);
	}

	private ActionListener toggleListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JCheckBox cb = ((JCheckBox) e.getSource());
			if (cb.isSelected())
				t.append(cb.getText() + " Set\n");
			else
				t.append(cb.getText() + " Cleared\n");
		}
	};

	public static void main(String[] args) {
		SwingConsole.run(CheckBoxes.class, 200, 300);
	}
}
