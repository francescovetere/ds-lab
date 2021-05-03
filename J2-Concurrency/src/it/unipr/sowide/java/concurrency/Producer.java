package it.unipr.sowide.java.concurrency;

public class Producer implements Runnable {
	private Buffer data;
	private int products;

	public Producer(final Buffer d, final int n) {
		this.data = d;
		this.products = n;
	}

	@Override
	public void run() {
		for (int i = 0; i < this.products; i++) {
			this.data.put(String.valueOf(i));
		}

		System.out.println("Producer managed " + this.products + " produts");
	}
}
