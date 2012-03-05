package thread;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiCore {
	// It's hard for me to say what happend inside JVM.
	// For small number of threads, multithreading runs even faster.
	// "Unbelievable!"
	// Fortunately, when the number goes up, its performance drops.
	// At least this is reasonable.
	public static final int CPU = 8;
	// public static final int CPU = 16;
	public static final int MAX = 1000000;
	public static CountDownLatch latch = new CountDownLatch(CPU);
	private static final ExecutorService pool = Executors
		.newFixedThreadPool(CPU);

	public static void hugTask() {
		long sum = 0;
		for (int i = 0; i < MAX; ++i) {
			sum += i * 5;
		}
		System.out.println("Hug taks is terminating with " + sum);
	}

	private static class ChildThread implements Runnable {
		@Override
			public void run() {
				hugTask();
				latch.countDown();
			}
	}

	public static class Test {
		public static void main(String[] args) {
			Date start, end;
			start = new Date();
			for (int i = 0; i < CPU; ++i) {
				hugTask();
			}
			end = new Date();

			System.out.println("Ellapsed time is "
					+ (end.getTime() - start.getTime()));
			System.out.println("***************************");
			start = new Date();
			for (int i = 0; i < CPU; ++i) {
				pool.execute(new ChildThread());
			}
			pool.shutdown();
			try {
				latch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			end = new Date();
			System.out.println("Ellapsed time is "
					+ (end.getTime() - start.getTime()));
		}
	}
}
