import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleWhiteBoard extends JFrame {
	protected int lastX, lastY;

	protected void record(int x, int y) {
		lastX = x;
		lastY = y;
	}

	public SimpleWhiteBoard () {
		getContentPane().setBackground(Color.black);
		// getContentPane().setForeground(Color.blue);
		setForeground(Color.blue);
		addMouseListener(new MouseAdapter() {
				public void mousePressed( MouseEvent e ) {
				record(e.getX(), e.getY() );
				}

				});

		addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged( MouseEvent e ) {
				getGraphics().drawLine(lastX, lastY, e.getX(), e.getY() );
				record(e.getX(), e.getY());
				}
				});
	
	}

	public static void main(String[] args) {
		CreateAndShowGUI.createAndShowGUI(SimpleWhiteBoard.class);
	}
}
