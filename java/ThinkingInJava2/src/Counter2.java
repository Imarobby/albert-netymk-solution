//The thread is embedded into class
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Counter2 extends JApplet implements Runnable {
	private int count = 0;
	private boolean runFlag = true;
	private Thread selfThread = null;
	private JButton start = new JButton("Start"),
			onOff = new JButton("Toggle");
	private JTextField t = new JTextField(10);

	public void run() {
		while (true) {
			try {
				selfThread.sleep(100);
			} catch (InterruptedException e) {
				System.err.println("Interrupted");
			}
			if (runFlag)
				t.setText(Integer.toString(count++));
		}
	}

	class StartL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (selfThread == null) {
				selfThread = new Thread(Counter2.this);
				selfThread.start();
			}
		}
	}

	class OnOffL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			runFlag = !runFlag;
		}
	}

	public void init() {
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(t);
		start.addActionListener(new StartL());
		cp.add(start);
		onOff.addActionListener(new OnOffL());
		cp.add(onOff);
	}

	public static void main(String[] args) {
		Console.run(new Counter2(), 300, 100);
	}
} // /:~
