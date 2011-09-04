import java.util.Random;

public class Agent {
	protected static Random rand = new Random();
	protected Environment env;
	// The position of the agent.
	protected int row, col;

	public Agent(Environment env) {
		this.env = env;
		row = rand.nextInt(Environment.HEIGHT);
		col = rand.nextInt(Environment.WIDTH);
	}

	public void move() {
	}

	public boolean IsDirty() {
		return env.IsDirty(row, col);
	}

	public void suck() {
		env.clean(row, col);
	}

}