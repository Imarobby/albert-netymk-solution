package gui.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.List;
import java.util.*;
import java.util.concurrent.*;

/**
 * Represent one horse in this race.
 */
public class Horse implements Runnable {
	private static Random rand = new Random(47);

	private CyclicBarrier barrier;
	private int id;
	private int strides;
	// connection between model and view
	private HorseView view;

	public Horse(HorseView p, int i, CyclicBarrier b) {
		view = p;
		id = i;
		barrier = b;
	}

	public synchronized int getStrides() {
		return strides;
	}

	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					strides += rand.nextInt(3);
				}   
				view.draw(getStrides());
				barrier.await();
			}   
		} catch(InterruptedException e) {
			// A legitimate way to exit
		} catch(BrokenBarrierException e) {
			// This one we want to know about
			throw new RuntimeException(e);
		}   
	}

	public String toString() {
		return "Horse " + id;
	}
}
