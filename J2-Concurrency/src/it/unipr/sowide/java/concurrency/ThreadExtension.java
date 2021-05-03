package it.unipr.sowide.java.concurrency;

import java.util.Random;

// Thread creato estendendo classe Thread
public final class ThreadExtension extends Thread {
	private static final int MAXTHREADS = 10;
	private static final int MAXTIME = 1000;

	private static final Random RANDOM = new Random();

	private ThreadExtension() {
	}

	@Override
	public void run() {
		try {
			// Faccio attendere il thread per un certo numero di millisecondi
			Thread.sleep(RANDOM.nextInt(MAXTIME));
		} catch (Exception e) {
			System.out.println("thread fails!");
		}

		System.out.println("thread ends!");
	}

	public static void main(final String[] args) {
		// Metto in esecuzione n threads
		int n = RANDOM.nextInt(MAXTHREADS);

		System.out.println(n + " threads will be started!");

		for (int i = 0; i < n; i++) {
			new ThreadExtension().start(); // Faccio partire il thread, e quindi eseguo la sua run()
		}
	}
}
