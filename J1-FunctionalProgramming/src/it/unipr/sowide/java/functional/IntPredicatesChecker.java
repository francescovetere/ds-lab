package it.unipr.sowide.java.functional;

public final class IntPredicatesChecker {
	private IntPredicatesChecker() {
	}

	public static boolean isPositive(final int n) {
		return n > 0;
	}

	public static boolean isEven(final int n) {
		return (n % 2) == 0;
	}
}
