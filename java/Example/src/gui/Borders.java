package gui;

import java.awt.*;
import java.awt.FlowLayout;
import java.lang.reflect.Constructor;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

public class Borders extends JFrame {
	static JPanel showBorder(Border b) {
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		String nm = b.getClass().toString();
		nm = nm.substring(nm.lastIndexOf('.') + 1);
		jp.add(new JLabel(nm, JLabel.CENTER),
				BorderLayout.CENTER);
		jp.setBorder(b);
		return jp;
	}
	public Borders() {
		setLayout(new GridLayout(2,4));
		add(showBorder(new TitledBorder("Title")));
		add(showBorder(new EtchedBorder()));
		add(showBorder(new LineBorder(Color.BLUE)));
		add(showBorder(
					new MatteBorder(5,5,30,30,Color.GREEN)));
		add(showBorder(
					new BevelBorder(BevelBorder.RAISED)));
		add(showBorder(
					new SoftBevelBorder(BevelBorder.LOWERED)));
		add(showBorder(new CompoundBorder(
						new EtchedBorder(),
						new LineBorder(Color.RED))));
		setVisible(true);
	}
	public static void main(String[] args) {
		SwingConsole.run(Borders.class, 500, 300);
	}
}
