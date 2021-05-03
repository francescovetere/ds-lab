package it.unipr.sowide.java.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * Coda con mutua esclusione, ma senza wait e signal (i.e. return null se non puo' fare put o get)
 */
public class SynchronizedMethodBuffer implements Buffer {
	private List<String> elements;

	private final int length;

	public SynchronizedMethodBuffer(final int l) {
		this.elements = new ArrayList<>();

		this.length = l;
	}

	@Override
	public int size() {
		return this.elements.size();
	}

	@Override
	public synchronized void put(final String s) {
		if (this.elements.size() < this.length) {
			this.elements.add(s);
		} else {
			// what operations should be done?
		}
	}

	@Override
	public synchronized String get() {
		if (this.elements.size() > 0) {
			return this.elements.remove(0);
		} else {
			return null; // Non è molto bello restituire null...
		}
	}
}
