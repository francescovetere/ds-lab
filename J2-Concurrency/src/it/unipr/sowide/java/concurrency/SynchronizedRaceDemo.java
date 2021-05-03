package it.unipr.sowide.java.concurrency;

// Tento di risolvere la race condition con join (vedi riga 22)
// Ma ancora non funziona, perchè i thread potrebbero comunque sovrapporsi nelle esecuzioni
public class SynchronizedRaceDemo extends Thread {
	private static final int THREADS = 100;
	private static final int OPERATIONS = 1000;

	private static int shared = 0;

	private Thread thread;

	public SynchronizedRaceDemo(final Thread t) {
		this.thread = t;
	}

	@Override
	public void run() {
		for (int i = 0; i < OPERATIONS; i++) {
			shared++;
		}
		
		// Ciascun thread al termine delle sue operazioni fa join, ovvero:
		// i thread che al momento sono running attendono che il thread che ha fatto join abbia finito di eseguire
		// Tutti attendono, tranne il primo thread! Infatti per lui this.thread è null
		joinThread(this.thread);
	}

	private static void joinThread(final Thread t) {
		if (t != null) {
			try {
				t.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(final String[] args) {
		Thread t = null;
		
		// Creo THREADS threads e li faccio partire
		for (int j = 0; j < THREADS; j++) {
			t = new SynchronizedRaceDemo(t);

			t.start();
		}

		joinThread(t);

		System.out.println("\n shared value is: " + shared);
	}
}
