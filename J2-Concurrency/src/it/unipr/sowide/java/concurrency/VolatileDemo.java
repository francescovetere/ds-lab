package it.unipr.sowide.java.concurrency;

// Esempio in cui creo 1 thread che usa variabili volatile
//  ==> variabile sempre letta/scritta da RAM ==> meno efficienza
// MA è l'unico modo per fare funzionare questo esempio! Senza volatile non funziona ;)
public class VolatileDemo extends Thread {
	private volatile boolean keepRunning = true;

	public VolatileDemo() {
		this.keepRunning = true;
	}

	@Override
	public void run() {
		System.out.print("\n Thread terminated in ");

		long t = System.currentTimeMillis();

		while (keepRunning) {
		}

		System.out.println((System.currentTimeMillis() - t) + " milliseconds");
	}

	public static void main(final String[] args) throws InterruptedException {
		VolatileDemo demo = new VolatileDemo();

		demo.start();
		Thread.sleep(1000);

		demo.keepRunning = false;
	}
}
