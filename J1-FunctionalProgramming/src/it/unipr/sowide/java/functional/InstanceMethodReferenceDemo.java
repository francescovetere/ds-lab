package it.unipr.sowide.java.functional;

import java.util.Random;

public class InstanceMethodReferenceDemo {
	private static final int MAX = 50;

	private int num;
	private Random random;

	public InstanceMethodReferenceDemo() {
		this.random = new Random();

		this.num = this.random.nextInt(MAX);
	}

	public int getNum() {
		return this.num;
	}

	boolean isBigger(final int n) {
		return this.num > n;
	}

	public static void main(final String[] args) {
		InstanceMethodReferenceDemo demo = new InstanceMethodReferenceDemo();

		int numToCompare = demo.random.nextInt(MAX);
		// Modo 3: uso un metodo NON statico di un oggetto
		IntPredicate p = demo::isBigger;

		if (p.check(numToCompare)) {
			System.out.println(demo.getNum() + " is bigger than " + numToCompare);
		} else {
			System.out.println(demo.num + " is smaller or equal than " + numToCompare);
		}
	}
}