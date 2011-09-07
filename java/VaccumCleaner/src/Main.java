import gui.SwingConsole;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	private static ExecutorService exec = Executors.newCachedThreadPool();
	private static Dimension dim = new Dimension(400, 500);
	private CountDownLatch latch;
	EnvironmentView[] playgrounds;
	JButton prepare;
	JButton run;

	Environment env;
	Agent[] agents;

	VacuumCleanerController[] controllers;

	public Main() {
		controllers = new VacuumCleanerController[2];
		agents = new Agent[controllers.length];
		agents[0] = new SimpleReflexAgent(new Environment());
		agents[1] = new ModelBasedAgent2(new Environment());
		playgrounds = new EnvironmentView[controllers.length];
		playgrounds[0] = new EnvironmentView(agents[0], dim);
		playgrounds[1] = new EnvironmentView(agents[1], dim);


		latch = new CountDownLatch(controllers.length);
		env = new Environment();

		prepare = new JButton("Prepare");
		run = new JButton("Run");
		prepare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (controllers[0] != null) {
					for(VacuumCleanerController varController : controllers) {
						varController.setTerminate(true);
					}
					try {
						latch.await();
					} catch(InterruptedException e) {
						System.out.println("Main is interrupted");
					}
				}
				env.random();
				for(EnvironmentView p : playgrounds) {
						p.initUsingEnvironment(env);
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
				// Since all the threads share the same state, all of them are terminated if the first is.
				if (controllers[0] == null || controllers[0].getTerminate()) {
					VacuumCleanerController.setBarrier(new CyclicBarrier(controllers.length));
					for(int i=0; i<controllers.length; ++i) {
						controllers[i] = new VacuumCleanerController(playgrounds[i], agents[i], latch);
						exec.execute(controllers[i]);
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
			SwingConsole.run(Main.class);
		}
	}
}

class VacuumCleanerController implements Runnable {
	private static CyclicBarrier barrier;
	private CountDownLatch latch;
	EnvironmentView playground;
	Agent agent;
	private boolean terminated;

	public VacuumCleanerController(EnvironmentView playground, Agent agent, CountDownLatch latch) {
		this.playground = playground;
		this.agent = agent;
		this.latch = latch;
	}

	public static void setBarrier(CyclicBarrier b) {
		 barrier = b;
	}

	// "terminated" variable is accessed by two threads; one is the controllers, the other is main thread.
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
				playground.update("Step: " + (i + 1));
				try {
					barrier.await();
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
			setTerminate(true);
			latch.countDown();
		}
}
