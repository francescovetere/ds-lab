package it.unipr.sowide.java.concurrency;

// Race condition: piu' thread provano a incrementare una stessa variabile condivisa dal loro unico processo
public final class ThreadRaceDemo {
	private static final int THREADS = 100;
	private static final int OPERATIONS = 1000;

	private static int shared = 0;

	private ThreadRaceDemo() {
	}

	public static void main(final String[] args) {
		Runnable runnable = () -> {
			for (int i = 0; i < OPERATIONS; i++) {
				shared++; // overlap di piu' thread!
			}
		};

		for (int j = 0; j < THREADS; j++) {
			Thread t = new Thread(runnable);
			t.start();
		}
		
		// Ogni thread dovrebbe incrementare shared di 1000, quindi
		// mi aspetto che shared valga 100*1000 = 100000
		// Ma non è così, i thread si pestano i piedi a vicenda
		// Addirittura quando arrivo a questa print, 
		// alcuni thread potrebbero ancora non aver terminato l'esecuzione
		System.out.println("\n shared value is: " + shared);
	}
}
