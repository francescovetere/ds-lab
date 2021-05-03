package it.unipr.sowide.java.functional;

import java.util.Random;

public class ConstructorReferenceDemo {
	private int num;

	public ConstructorReferenceDemo(final int n) {
		System.out.println("First constructor");
		this.num = n;
	}

	public ConstructorReferenceDemo(final ConstructorReferenceDemo i) {
		System.out.println("Second constructor");
		this.num = i.getNum();
	}

	public int getNum() {
		return this.num;
	}

	public static void main(final String[] args) {
		final int max = 50;

		Random r = new Random();

		int num = r.nextInt(max);

		// Modo 3: uso un costruttore
		IntSupplier s1 = ConstructorReferenceDemo::new;

		// Usa apply di IntSupplier
		// Ma la apply è stata implementata con il costruttore ConstructorReferenceDemo(final int n)!
		ConstructorReferenceDemo newObj1 = s1.apply(num);

		System.out.println("new object has a instance value " + newObj1.num);

		// Modo 3: uso un costruttore
		// Ma la apply è stata implementata con il costruttore ConstructorReferenceDemo(final ConstructorReferenceDemo i)!
		ObjectSupplier s2 = ConstructorReferenceDemo::new;

		ConstructorReferenceDemo newObj = s2.apply(newObj1); // Usa apply di ObjectSupplier

		System.out.println("new object has a instance value " + newObj.num);
	}
}
