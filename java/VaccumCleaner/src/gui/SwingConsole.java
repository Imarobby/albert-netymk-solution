package gui;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.lang.reflect.InvocationTargetException;

public class SwingConsole {
	public static void run(final int width, final int height, final Class<? extends JFrame> type) {
		run(width, height, UIManager.getSystemLookAndFeelClassName(), type);
	}

	public static void run(final int width, final int height, final String lookAndFeel,
		   	final Class<? extends JFrame> type) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(lookAndFeel);
				} catch(Exception e) {
					e.printStackTrace();
				}
				try {
					JFrame frame = type.getConstructor().newInstance();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(width, height);

					frame.setLocationRelativeTo(null);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
