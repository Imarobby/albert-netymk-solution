//Making a lot of threads
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Counter3 extends JApplet {
	private JButton start = new JButton("Start");
	private boolean started = false;
	private Ticker[] s;
	private boolean isApplet = true;
	private int size = 2;

	class Ticker extends Thread {
		private JButton b = new JButton("Toggle");
		private JTextField t = new JTextField(10);
		private int count = 0;
		private boolean runFlag = true;

		public Ticker() {
			b.addActionListener(new ToggleL());
			JPanel p = new JPanel();
			p.add(t);
			p.add(b);
			// Calls JApplet.getContentPane().add():
			getContentPane().add(p);
		}

		class ToggleL implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				runFlag = !runFlag;
			}
		}

		public void run() {
			while (true) {
				if (runFlag)
					t.setText(Integer.toString(count++));
				try {
					sleep(100);
				} catch (InterruptedException e) {
					System.err.println("Interrupted");
				}
			}
		}
	}

	class StartL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!started) {
				started = true;
				for (int i = 0; i < s.length; i++)
					s[i].start();
			}
		}
	}

	public void init() {
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		// Get parameter "size" from Web page:
		if (isApplet) {
			String sz = getParameter("size");
			if (sz != null)
				size = Integer.parseInt(sz);
		}
		s = new Ticker[size];
		for (int i = 0; i < s.length; i++)
			s[i] = new Ticker();
		start.addActionListener(new StartL());
		cp.add(start);
	}

	public static void main(String[] args) {
		Counter3 applet = new Counter3();
		// This isn't an applet, so set the flag and
		// produce the parameter values from args:
		applet.isApplet = false;
		if (args.length != 0)
			applet.size = Integer.parseInt(args[0]);
		Console.run(applet, 200, applet.size * 50);
	}
} // /:~