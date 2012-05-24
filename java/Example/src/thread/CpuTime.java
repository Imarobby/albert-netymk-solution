package thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CpuTime {
	private static class LittleThread implements Runnable {
		private ThreadMXBean mx = ManagementFactory.getThreadMXBean();

		@Override
		public void run() {
			long startSystem = System.nanoTime();
			long startCpu = mx.getCurrentThreadCpuTime();
			double dummy = 0;
			for (int i = 0; i < 1000000; ++i) {
				dummy += Math.random();
			}
			System.out.println(dummy);
			long endSystem = System.nanoTime();
			long endCpu = mx.getCurrentThreadCpuTime();

			System.out.println("thread " + Thread.currentThread().getId()
					+ " execution time: " + (endSystem - startSystem));
			System.out.println("thread " + Thread.currentThread().getId()
					+ " cpu time: " + (endCpu - startCpu));
		}

	}

	public static class Test {
		public static void main(String[] args) {
			ThreadMXBean mx = ManagementFactory.getThreadMXBean();
			long start = mx.getCurrentThreadCpuTime();
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new LittleThread());

			exec.shutdown();
			long end = mx.getCurrentThreadCpuTime();
			System.out.println("main thread " 
					+ Thread.currentThread().getId()
					+ " cpu time: " + (end - start));
		}
	}
}
