package it.unipr.sowide.java.concurrency;

public class Consumer implements Runnable {
	private Buffer data;
	private int products;

	public Consumer(final Buffer d, final int p) {
		this.data = d;
		this.products = p;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.products; i++) {
			this.data.get();
		}

		System.out.println("Consumer managed " + this.products + " produts");
	}
}
