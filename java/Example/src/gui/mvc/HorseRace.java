package gui.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.List;
import java.util.*;
import java.util.concurrent.*;

import gui.SwingConsole;

public class HorseRace extends JFrame {
	public static final int FINISH_LINE = 100;

	private ExecutorService exec = Executors.newCachedThreadPool();
	private List<Horse> horses = new ArrayList<Horse>();
	private CyclicBarrier barrier;

	public HorseRace() {
		super("Horse race");
		int nHorses = 7;
		final int pause = 200;
		setLayout(new GridLayout(nHorses, 1));
		barrier = new CyclicBarrier(nHorses, new Runnable() {
			public void run() {
				for(Horse h : horses) {
					if(h.getStrides() >= FINISH_LINE) {
						exec.shutdownNow();
						JOptionPane.showMessageDialog(null, h + " wins", "Result",
							JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch(InterruptedException e) {
					System.out.println("barrier-action sleep interrupted");
				}
			}
		});
		for(int i = 0; i < nHorses; ++i) {
			HorseView panel = new HorseView();
			add(panel);
			Horse h = new Horse(panel, i, barrier);
			horses.add(h);
			exec.execute(h);
		}
	}

	public HorseRace(int nHorses, final int pause) {
		setLayout(new GridLayout(nHorses, 1));
		barrier = new CyclicBarrier(nHorses, new Runnable() {
			public void run() {
				for(Horse h : horses) {
					if(h.getStrides() >= FINISH_LINE) {
						exec.shutdownNow();
						JOptionPane.showMessageDialog(null, h + " wins", "Result",
							JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch(InterruptedException e) {
					System.out.println("barrier-action sleep interrupted");
				}
			}
		});
		for(int i = 0; i < nHorses; ++i) {
			HorseView panel = new HorseView();
			add(panel);
			Horse h = new Horse(panel, i, barrier);
			horses.add(h);
			exec.execute(h);
		}
	}

	public static void main(String[] args) {
		SwingConsole.run(HorseRace.class, 800, 600);
	}
}
