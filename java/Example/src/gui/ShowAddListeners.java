package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ShowAddListeners extends JFrame {
	private static Pattern addListener = Pattern
			.compile("(add\\w+?Listener)(\\(.*?\\))");
	private static Pattern qualifier = Pattern.compile("\\w+\\.");
	private JTextField name;
	private JTextArea results;

	public ShowAddListeners() {
		setLayout(new FlowLayout());
		name = new JTextField(25);
		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nm = name.getText().trim();
				if (nm.length() == 0) {
					results.setText("No match");
					return;
				}
				Class<?> kind;
				try {
					kind = Class.forName("javax.swing." + nm);
				} catch (ClassNotFoundException ex) {
					results.setText("No match");
					return;
				}
				Method[] methods = kind.getMethods();
				results.setText("");
				for (Method m : methods) {
					Matcher matcher = addListener.matcher(m.toString());
					if (matcher.find()) {
						// results.append(matcher.group(1) + "\n");
						results.append(matcher.group(1)
								+ qualifier.matcher(matcher.group(2))
										.replaceAll("") + "\n");
					}
				}
			}
		});

		results = new JTextArea(40, 60);
		add(name);
		add(results);
	}

	public static void main(String[] args) {
		SwingConsole.run(ShowAddListeners.class, 500, 600);
	}
}
