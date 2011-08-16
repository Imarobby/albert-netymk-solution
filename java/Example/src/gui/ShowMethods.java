package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ShowMethods extends JFrame {
	private static String usage = "usage:\n"
			+ "ShowMethods qualified.class.name\n"
			+ "To show all methods in class or:\n"
			+ "ShowMethods qualified.class.name word\n"
			+ "To search for methods involving 'word'";
	private static Pattern p = Pattern.compile("\\w+\\.");

	private JTextField name;
	private JTextArea result;

	public ShowMethods() {
		setLayout(new FlowLayout());
		name = new JTextField(20);
		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] input = name.getText().trim().split(" ");
				if (input.length == 1) {
					try {
						Class<?> c = Class.forName(input[0]);
						Method[] methods = c.getMethods();
						Constructor<?>[] ctors = c.getConstructors();
						for (Method method : methods) {
							result.append(p.matcher(method.toString())
									.replaceAll(""));
						}
						for (Constructor<?> ctor : ctors) {
							result.append(p.matcher(ctor.toString())
									.replaceAll(""));
						}
					} catch (ClassNotFoundException e1) {
						result.append("No class:" + e1);
					}

				}
				if (input.length == 2) {
					try {
						Class<?> c = Class.forName(input[0]);
						Method[] methods = c.getMethods();
						Constructor<?>[] ctors = c.getConstructors();
						for (Method method : methods) {
							if (method.toString().indexOf(input[1]) != -1) {
								result.append(p.matcher(method.toString())
										.replaceAll(""));
							}
							for (Constructor<?> ctor : ctors) {
								if (ctor.toString().indexOf(input[1]) != -1) {
									result.append(p.matcher(ctor.toString())
											.replaceAll(""));
								}
							}
						}
					} catch (ClassNotFoundException e1) {
						result.append("No class:" + e1);
					}
				}
				result = new JTextArea(40, 60);
				add(name);
				add(result);
			}
		});
	}

	public static void main(String[] args) {
		SwingConsole.run(ShowMethods.class, 300, 500);
	}
}