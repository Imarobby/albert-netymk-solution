import java.util.Random;

public class Agent {
	protected static Random rand = new Random();
	protected final static int LIFETIME = 30;
	protected Environment env;
	// The position of the agent.
	protected int row, col;
	protected boolean finished;

	public Agent(Environment env) {
		this.env = env;
	}

	public void random() {
		row = rand.nextInt(Environment.HEIGHT);
		col = rand.nextInt(Environment.WIDTH);
		setFinish(false);
	}

	public void setPosition(int i, int j) {
		row = i;
		col = j;
	}

	public void init() {
		row = 0;
		col = 0;
	}

	public void move() {
	}

	public void setFinish(boolean b) {
		finished = b;
	}

	public boolean getFinish() {
		return finished;
	}

	public boolean IsDirty() {
		return env.IsDirty(row, col);
	}

	public void suck() {
		env.clean(row, col);
	}

	public String toString() {
		return "Position: " + row + " " + col;
	}
}
