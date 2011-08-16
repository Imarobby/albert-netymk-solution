package gui;

import java.lang.reflect.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

public class TabbedPane extends JFrame {
	private String[] flavors = {
		"Chocolate", "Strawberry", "Vanilla Fudge Swirl",
		"Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
		"Praline Cream", "Mud Pie"
	};
	private JTabbedPane tabs = new JTabbedPane();
	private JTextField txt = new JTextField(20);
	public TabbedPane() {
		int i = 0;
		for(String flavor : flavors)
			tabs.addTab(flavors[i],
					new JButton("Tabbed pane " + i++));
		tabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txt.setText("Tab selected: " +
					tabs.getSelectedIndex());
			}
		});
		add(BorderLayout.SOUTH, txt);
		add(tabs);
	}
	public static void main(String[] args) {
		SwingConsole.run(TabbedPane.class, 400, 250);
	}
}
