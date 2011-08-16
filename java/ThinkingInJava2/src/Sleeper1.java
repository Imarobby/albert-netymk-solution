import java.awt.Container;

class Sleeper1 extends Blockable {
	public Sleeper1(Container c) {
		super(c);
	}

	public synchronized void run() {
		while (true) {
			i++;
			update();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Interrupted");
			}
		}
	}
}

class Sleeper2 extends Blockable {
	public Sleeper2(Container c) {
		super(c);
	}

	public void run() {
		while (true) {
			change();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Interrupted");
			}
		}
	}

	public synchronized void change() {
		i++;
		update();
	}
}