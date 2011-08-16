import javax.swing.JApplet;

/**
 * Display the data - time vs. position - for a dropped mass by using an
 * instance of DataPanel .
 * 
 **/
public class DataPlotApplet extends JApplet {
	DrawPoints fDrawPoints;

	/**
	 * Create a DataPanel object and add it to the content pane.
	 **/
	public void init() {

		// Create the points draw function
		fDrawPoints = new DrawPoints();

		// and a DrawFunction array to pass to the DrawPanel
		DrawFunction[] draw_functions = new DrawFunction[1];
		draw_functions[0] = fDrawPoints;

		// Create an instance of a JPanel sub-class
		// but don't plot anything yet.
		DrawPanel draw_panel = new DrawPanel(-5.0, 1.0, 0.0, 1.2,
				draw_functions);

		draw_panel.setTitle("Dropped Mass");
		draw_panel.setXLabel("Y (m) vs Time (sec)");

		// And add one or more panels to the JApplet panel.
		add(draw_panel);
	} // init

	/**
	 * Create some data to illustrate the drawing of error bars with this
	 * PlotPanel subclass. Pass the data points and error values to the
	 * DataPanel object.
	 **/
	public void start() {
		// Variables
		double vy;
		double total_t;
		int n;

		// Constants
		double y0 = 0.0;
		double v0 = 0.0;
		double g = -9.80;// meter per sec**2

		// Version 1
		double dt = 0.1;
		int n_steps = 10;
		vy = v0;

		double[] y = new double[n_steps];
		double[] t = new double[n_steps];
		double[] y_err = new double[n_steps];
		double[] t_err = new double[n_steps];

		y[0] = y0;
		t[0] = 0.0;

		for (n = 1; n < n_steps; n++) {
			y[n] = y[n - 1] + vy * dt;
			vy = vy + g * dt;
			t[n] = n * dt;

			// Create some dummy error data for
			// the display.
			y_err[n] = 0.3 * y[n];
			t_err[n] = 0.3 * t[n];
		}

		total_t = n_steps * dt;

		// Create a 2d array to pass the points data
		// to the DrawPoints object.
		double[][] data = new double[4][];
		data[0] = y;
		data[1] = t;
		data[2] = y_err;
		data[3] = t_err;

		fDrawPoints.setParameters(null, data);

		// The DrawPanel will now repaint itself
		// using the DrawPoints object.
		repaint();

		System.out.println("Version 1, dt=0.1 ");
		System.out.println("Time = " + total_t);
		System.out.println("y = " + y[n_steps - 1] + ", vy = " + vy);
		System.out.println("");

	} // start

} // DataPlotApplet 