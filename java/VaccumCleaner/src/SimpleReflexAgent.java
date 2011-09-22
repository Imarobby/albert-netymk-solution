public class SimpleReflexAgent extends Agent {
	private Direction dir;
	private boolean forward;

	public SimpleReflexAgent(Environment env) {
		super(env);
		forward = true;
	}

	public boolean IsDirty() {
		return super.IsDirty();
	}

	public void move() {
		// We hard code it to go right
		if(col < Environment.WIDTH-1) {
			super.move();
			col++;
		}
	}
}
