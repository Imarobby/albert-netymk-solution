package gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.Timer;

public class TimerClock extends JFrame {

	private Icon _icon;

	public TimerClock() {
		super("Java clock");
		setSize(350, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		_icon = new StringIcon();

		getContentPane().add(_icon);

		setResizable(false);

		Timer t = new javax.swing.Timer(1000, new ClockListener());
		t.start();
	}

	private class ClockListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			_icon.paintIcon(null, getGraphics(), 0, 30);
		}
	}

	public static void main(String[] args) {
		JFrame clock = new TimerClock();
	}
}

class StringIcon implements Icon {

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Calendar calendar = Calendar.getInstance(TimeZone
				.getTimeZone("Europe/Stockholm"));
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		formatter.setCalendar(calendar);
		g.drawString(formatter.format(calendar.getTime()), x, y);
	}
}
