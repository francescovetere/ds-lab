package it.unipr.sowide.java.concurrency;

// Esempio in cui creo 1 thread che usa variabili non volatile
// ==> variabile letta/scritta usando la cache ==> piu' efficienza
// MA non funziona!

public class NonVolatileDemo extends Thread {
	private boolean keepRunning = true;

	public NonVolatileDemo() {
		this.keepRunning = true;
	}

	@Override
	public void run() {
		System.out.print("\n Thread terminated in ");

		long t = System.currentTimeMillis();
		
		// Il thread continuerà a leggere keepRunning dalla cache, e il suo valore sarà sempre true!
		while (keepRunning) {
			// System.out.println(keepRunning);
		}
		
		// N.B.:
		// Non arriverà MAI qui! Perchè dopo 1000 ms il thread del main termina
		// Ma questo no, rimane sempre nel while
		// Questo è il motivo per cui il quadratino rosso della console rimane acceso
		System.out.println((System.currentTimeMillis() - t) + " milliseconds");
	}

	public static void main(final String[] args) throws InterruptedException {
		NonVolatileDemo demo = new NonVolatileDemo();

		demo.start();
		Thread.sleep(1000);
		
		demo.keepRunning = false;
	}
}
