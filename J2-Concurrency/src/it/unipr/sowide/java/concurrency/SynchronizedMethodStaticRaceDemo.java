package it.unipr.sowide.java.concurrency;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// Provo a risolvere la race condition con un metodo synchronized
// Ancora non funziona perchè sto incrementando una variabile statica
// Occorre operare sempre su un oggetto esterno, mai su una variabile statica!!!

public class SynchronizedMethodStaticRaceDemo extends Thread {
	private static final int THREADS = 100;
	private static final int OPERATIONS = 1000;
	private static final int COREPOOL = 5;
	private static final int MAXPOOL = 100;
	private static final long IDLETIME = 5000;
	private static final long SLEEPTIME = 10;
	
	private static int shared = 0;
	
	public synchronized void increment() {
		shared++;
	}

	@Override
	public void run() {
		for (int i = 0; i < OPERATIONS; i++) {
			increment();
		}
	}

	public static void main(final String[] args) {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

		for (int j = 0; j < THREADS; j++) {
			pool.execute(new SynchronizedMethodStaticRaceDemo());
		}

		while (pool.getActiveCount() > 0) {
			try {
				Thread.sleep(SLEEPTIME);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*** Altrimenti, mio metodo senza usare i pool (e mettendo chiaramente increment come static)***/
		/*
		SynchronizedMethodStaticRaceDemo thread = new SynchronizedMethodStaticRaceDemo();
		
		for (int j = 0; j < THREADS; ++j) {
			thread = new SynchronizedMethodStaticRaceDemo();
			thread.start();
		}
		
		// Se L'ULTIMO thread lanciato è ancora attivo, il main thread si rimette a dormire 
		while (thread.isAlive()) {
			try {
				Thread.sleep(SLEEPTIME);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// O ancora piu' semplicemente faccio join sempre sull'ultimo thread lanciato
		//	try {
		//		thread.join();
		//	} catch (InterruptedException e) {
		//		e.printStackTrace();
		//	}
		*/
		
		System.out.println("\n shared value is: " + shared);
	}
}
