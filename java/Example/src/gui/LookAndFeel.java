package gui;

import java.lang.reflect.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

public class LookAndFeel extends JFrame {
	private String[] choices =
		"Eeny Meeny Minnie Mickey Moe Larry Curly".split(" ");
	private Component[] samples = {
		new JButton("JButton"),
		new JTextField("JTextField"),
		new JLabel("JLabel"),
		new JCheckBox("JCheckBox"),
		new JRadioButton("Radio"),
		new JComboBox(choices),
		new JList(choices),
	};
	public LookAndFeel() {
		super("Look And Feel");
		setLayout(new FlowLayout());
		for(Component component : samples)
			add(component);
	}
	private static void usageError() {
		System.out.println(
				"Usage:LookAndFeel [cross|system]");
		System.exit(1);
	}
	public static void main(String[] args) {
		if(args.length == 0) usageError();
		if(args[0].equals("cross")) {
			SwingConsole.run(LookAndFeel.class, 300, 300,
					UIManager.getCrossPlatformLookAndFeelClassName());
		} else if(args[0].equals("system")) {
			SwingConsole.run(LookAndFeel.class, 300, 300,
				   	UIManager.getSystemLookAndFeelClassName());
		} else usageError();
	}
}
