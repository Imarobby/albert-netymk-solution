import java.util.Random;

public class Agent {
	protected static Random rand = new Random();
	protected final static int LIFETIME = 100;
	protected Environment env;
	// The position of the agent.
	protected int row, col;
	protected boolean finished;
	protected int performance;

	public Agent(Environment env) {
		this.env = env;
		performance = 0;
	}

	public void setPosition(int i, int j) {
		row = i;
		col = j;
	}

	public void init() {
		row = 0;
		col = 0;
		performance = 0;
		setFinish(false);
	}

	public void move() {
		performance--;
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
		performance += 10;
	}

	public String toString() {
		return "Position: " + row + " " + col;
	}
}
