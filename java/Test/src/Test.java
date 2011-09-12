import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Test extends JFrame {
	public Test() {
		add(new MyPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setSize(500,500);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Test();
	}
}
class MyPanel extends JPanel {
	public MyPanel() {
		setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBackground(Color.red);
	}
}
