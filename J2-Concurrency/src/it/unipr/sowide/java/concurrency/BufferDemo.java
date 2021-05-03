package it.unipr.sowide.java.concurrency;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class BufferDemo {
	private static final int COREPOOL = 5;
	private static final int MAXPOOL = 100;
	private static final long IDLETIME = 5000;
	private static final int PRODUCTS = 1000;
	private static final long SLEEPTIME = 10;
	private static final int BUFFERSIZE = 10;
	private static final int NODES = 10;

	private BufferDemo() {
	}

	public static void main(final String[] args) {
		System.out.println("Enter:");
		System.out.println(" w for using a buffer with wait and signal syncronization");
		System.out.println(" b for using a buffer with blocking queue syncronization");

		Scanner scanner = new Scanner(System.in);

		String s = scanner.next();

		scanner.close();

		Buffer b;

		if (s.equals("w")) {
			b = new SynchronizedMethodWaitSignalBuffer(BUFFERSIZE);
		} else {
			b = new BlockingQueueBuffer(BUFFERSIZE);
		}

		// La soluzione è pensata col solito ThreadPoolExecutor
		// Nei 4 commenti che seguono, è riportata la solzione alternativa usando dei semplici Thread
		ThreadPoolExecutor pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());
//		Thread t = null;
		
		for (int j = 0; j < NODES; j++) {
			System.out.println("Producer " + j);
//			t = new Thread(new Producer(b, PRODUCTS)); t.start();
			pool.execute(new Producer(b, PRODUCTS)); // Posso farlo perchè Producer(e Consumer) implementa Runnable, e pool.execute() richiede un Runnable
			
//			t = new Thread(new Consumer(b, PRODUCTS)); t.start();
			System.out.println("Consumer " + j);
			pool.execute(new Consumer(b, PRODUCTS));
			
			// N.B.: le put dei Producer e le get dei Consumer dipenderanno dall'implementazione scelta dall'utente ("w" o no)
		}

		while (pool.getActiveCount() > 0) {
//		while(t.isAlive()) {
			try {
				Thread.sleep(SLEEPTIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		pool.shutdown();
	}
}
