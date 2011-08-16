import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Sharing1 extends JApplet {
	private static int accessCount = 0;
	private static JTextField aCount = new JTextField("0", 7);

	public static void incrementAccess() {
		accessCount++;
		aCount.setText(Integer.toString(accessCount));
	}

	private JButton start = new JButton("Start"),
			watcher = new JButton("Watch");
	private boolean isApplet = true;
	private int numCounters = 2;
	private int numWatchers = 2;
	private TwoCounter[] s;

	class TwoCounter extends Thread {
		private boolean started = false;
		private JTextField t1 = new JTextField(5), t2 = new JTextField(5);
		private JLabel l = new JLabel("count1 == count2");
		private int count1 = 0, count2 = 0;

		// Add the display components as a panel:
		public TwoCounter() {
			JPanel p = new JPanel();
			p.add(t1);
			p.add(t2);
			p.add(l);
			getContentPane().add(p);
		}

		public void start() {
			if (!started) {
				started = true;
				super.start();
			}
		}

		public void run() {
			while (true) {
				t1.setText(Integer.toString(count1++));
				t2.setText(Integer.toString(count2++));
				try {
					sleep(500);
				} catch (InterruptedException e) {
					System.err.println("Interrupted");
				}
			}
		}

		public void synchTest() {
			Sharing1.incrementAccess();
			if (count1 != count2)
				l.setText("Unsynched");
		}
	}

	class Watcher extends Thread {
		public Watcher() {
			start();
		}

		public void run() {
			while (true) {
				for (int i = 0; i < numCounters; i++)
					s[i].synchTest();
				try {
					sleep(500);
				} catch (InterruptedException e) {
					System.err.println("Interrupted");
				}
			}
		}
	}

	class StartL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < numCounters; i++)
				s[i].start();
		}
	}

	class WatcherL implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < numWatchers; i++)
				new Watcher();
		}
	}

	public void init() {
		if (isApplet) {
			String counters = getParameter("size");
			if (counters != null)
				numCounters = Integer.parseInt(counters);
			String watchers = getParameter("watchers");
			if (watchers != null)
				numWatchers = Integer.parseInt(watchers);
		}
		s = new TwoCounter[numCounters];
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		for (int i = 0; i < numCounters; i++)
			s[i] = new TwoCounter();
		JPanel p = new JPanel();
		start.addActionListener(new StartL());
		p.add(start);
		watcher.addActionListener(new WatcherL());
		p.add(watcher);
		p.add(new JLabel("Access Count"));
		p.add(aCount);
		cp.add(p);
	}

	public static void main(String[] args) {
		Sharing1 applet = new Sharing1();
		// This isn't an applet, so set the flag and
		// produce the parameter values from args:
		applet.isApplet = false;
		applet.numCounters = (args.length == 0 ? 2 : Integer.parseInt(args[0]));
		applet.numWatchers = (args.length < 2 ? 5 : Integer.parseInt(args[1]));
		Console.run(applet, 1000, applet.numCounters * 50);
	}
} // /:~
