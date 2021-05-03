package it.unipr.sowide.java.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * Coda con mutua esclusione, e con wait e signal (i.e. se non puo' fare put o get, fa wait)
 */
public class SynchronizedMethodWaitSignalBuffer implements Buffer {
	private List<String> elements;

	private final int length;

	public SynchronizedMethodWaitSignalBuffer(final int l) {
		this.elements = new ArrayList<>();

		this.length = l;
	}

	@Override
	public int size() {
		return this.elements.size();
	}

	@Override
	public synchronized String get() {
		while (this.elements.size() == 0) {
			try {
				System.out.println("get ==> wait");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		notifyAll();
		
		System.out.println("get: size " + elements.size());
		return this.elements.remove(0);
	}

	@Override
	public synchronized void put(final String s) {
		while (this.elements.size() == this.length) {
			try {
				System.out.println("put ==> wait");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		this.elements.add(s);
		notifyAll();
		
		System.out.println("put: size " + elements.size());
	}
}
