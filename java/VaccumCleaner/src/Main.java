import gui.SwingConsole;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	private CountDownLatch latch;
	EnvironmentView[] playgrounds;
	JButton prepare;
	JButton run;

	Environment env;
	Agent[] agents;

	VacuumCleanerController[] controller;

	public Main() {
		controller = new VacuumCleanerController[2];
		latch = new CountDownLatch(controller.length);
		env = new Environment();
		agents = new Agent[controller.length];
		agents[0] = new SimpleReflexAgent(new Environment());
		agents[1] = new ModelBasedAgent2(new Environment());
		playgrounds = new EnvironmentView[controller.length];
		playgrounds[0] = new EnvironmentView(agents[0], new Dimension(400, 400));
		playgrounds[1] = new EnvironmentView(agents[1], new Dimension(400, 400));

		prepare = new JButton("Prepare");
		run = new JButton("Run");
		prepare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (controller[0] != null) {
					controller[0].setTerminate(true);
					try {
						latch.await();
					} catch(InterruptedException e) {
						System.out.println("Main is interrupted");
					}
				}
				env.random();
				for(EnvironmentView p : playgrounds) {
						p.setEnvironment(env);
				}
				for(Agent a : agents) {
					a.init();
				}
				for(EnvironmentView p : playgrounds) {
					p.update();
				}
			}
		});
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0; i<controller.length; ++i) {
					if (controller[i] == null || controller[i].getTerminate()) {
						controller[i] = new VacuumCleanerController(playgrounds[i], agents[i], latch);
						exec.execute(controller[i]);
					}
				}
			}
		});

		setLayout(new FlowLayout());
		for( JPanel p : playgrounds) {
			add(p);
		}
		add(prepare);
		add(run);
		setVisible(true);
	}

	public static class Test {
		public static void main(String[] argv) {
			SwingConsole.run(1000, 800, Main.class);
		}
	}
}

class VacuumCleanerController implements Runnable {
	private CountDownLatch latch;
	EnvironmentView playground;
	Agent agent;
	private boolean terminated;

	public VacuumCleanerController(EnvironmentView playground, Agent agent, CountDownLatch latch) {
		this.playground = playground;
		this.agent = agent;
		this.latch = latch;
	}

	// teminated variable is accessed by two threads; one is the controller, the other is main thread.
	public synchronized void setTerminate(boolean b) {
		terminated = b;
	}

	public synchronized boolean getTerminate() {
		return terminated;
	}

	@Override
		public void run() {
			for (int i = 0; i < Agent.LIFETIME; ++i) {
				if (getTerminate()) {
					break;
				}
				if (agent.IsDirty()) {
					agent.suck();
				} else {
					agent.move();
				}
				playground.update();
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			setTerminate(true);
			latch.countDown();
		}
}
