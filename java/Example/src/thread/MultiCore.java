package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiCore {
	// It's hard for me to say what happened inside JVM.
	// For small number of threads, multithreading runs even faster.
	// "Unbelievable!"
	// Maybe it's becauese my OS is preemptive.
	// Fortunately, when the number goes up, its performance drops.
	// At least this is reasonable.
	public static final int CPU = 2;
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
			long start, end;
			start = System.nanoTime();
			for (int i = 0; i < CPU; ++i) {
				hugTask();
			}
			end = System.nanoTime();

			System.out.println("Ellapsed time is " + (end - start));
			System.out.println("***************************");
			start = System.nanoTime();
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
			end = System.nanoTime();
			System.out.println("Ellapsed time is " + (end - start));
		}
	}
}
