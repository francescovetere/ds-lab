package it.unipr.sowide.java.concurrency;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Coda con mutua esclusione, e con wait e signal (i.e. se non puo' fare put o get, fa wait)
 * MA wait e signal sono fatte in modo automatico!
 * Semplicemente, invece che usare add e remove, uso put e take
 */
public class BlockingQueueBuffer implements Buffer {
	private ArrayBlockingQueue<String> elements;

	public BlockingQueueBuffer(final int l) {
		this.elements = new ArrayBlockingQueue<String>(l);
	}

	@Override
	public int size() {
		return this.elements.size();
	}

	@Override
	public String get() {
		try {
			// come remove(0), ma con sincronizzazione implicita
			// è bloccante quindi: se non ci sono elementi nella coda, si addormenta in automatico
			return this.elements.take();
		} catch (InterruptedException e) {
			return null;
		}
	}

	@Override
	public void put(final String s) {
		try {
			// come add(s), ma con sincronizzazione implicita
			// è bloccante quindi: se ci sono gia' max elementi nella coda, si addormenta in automatico
			this.elements.put(s);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
