import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Console {
	// Create a title string from the class name:
	public static String title(Object o) {
		return o.getClass().getName();
	}

	public static void setupClosing(JFrame frame) {
		// The improved solution in JDK 1.3:
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void run(JFrame frame, int width, int height) {
		setupClosing(frame);
		frame.setSize(width, height);
		frame.setVisible(true);
	}

	public static void run(JApplet applet, int width, int height) {
		JFrame frame = new JFrame(title(applet));
		setupClosing(frame);
		frame.getContentPane().add(applet);
		frame.setSize(width, height);
		applet.init();
		applet.start();
		frame.setVisible(true);
	}

	public static void run(JPanel panel, int width, int height) {
		JFrame frame = new JFrame(title(panel));
		setupClosing(frame);
		frame.getContentPane().add(panel);
		frame.setSize(width, height);
		frame.setVisible(true);
	}
}
