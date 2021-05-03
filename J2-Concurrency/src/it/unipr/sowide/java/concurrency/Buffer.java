package it.unipr.sowide.java.concurrency;

public interface Buffer {
	int size();

	String get();

	void put(String s);
}
