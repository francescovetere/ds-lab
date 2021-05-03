package it.unipr.sowide.java.concurrency;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// Tento di risolvere la race condition con un pool di thread
// Ma ancora non funziona! Perchè ho risolto la join, ma ancora non la concorrenza nell'incremento!
// Soluzione ==> Servono i metodi sincronizzati
// (?) Come mai alcuni thread non terminano? (quadratino console resta rosso)
// 		Secondo me ci manca pool.shutdown() ...
// ==> Esatto, avevo ragione!

public final class ThreadPoolRaceDemo {
	private static final int THREADS = 100;
	private static final int OPERATIONS = 1000;
	private static final int COREPOOL = 5;
	private static final int MAXPOOL = 100;
	private static final long IDLETIME = 5000;
	private static final long SLEEPTIME = 10;

	private static int shared = 0;

	private ThreadPoolRaceDemo() {
	}

	public static void main(final String[] args) {
		Runnable runnable = () -> {
			for (int i = 0; i < OPERATIONS; i++) {
				shared++;
			}
		};
		
		/*
		 * Specifico che voglio al max COREPOOL running allo stesso momento,
		 * e che ne posso gestire al massimo MAXPOOL tramite un round robin
		 * Dopo IDLETIME ms, i thread running devono cedere il posto ad altri thread nel pool
		 * I thread non running sono mantenuti in una LinkedBlockingQueue
		 * 
		 * (Ogni pool si occuperà di fare 1000 iterazioni del ciclo for)
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

		for (int j = 0; j < THREADS; j++) {
			pool.execute(runnable);
		}
		
		// Finchè ci sono thread attivi nel pool, il main thread resta addormentato
		while (pool.getActiveCount() > 0) {
			try {
				Thread.sleep(SLEEPTIME);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// pool.shutdown(); (?) Non ci vorrebbe questo?
		pool.shutdown();
		
		System.out.println("\n shared value is: " + shared);
		
		
	}
}
