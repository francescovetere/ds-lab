package it.unipr.sowide.java.functional;

import java.util.Arrays;
import java.util.List;

public final class NumbersDemo {
	private NumbersDemo() {
	}

	public static void main(final String[] args) {
		List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		// Filtro i soli elementi pari e li stampo
		System.out.println("Even elements:");
		values.stream().filter(v -> ((v % 2) == 0)).forEach(v -> System.out.println(v));
		
		// Filtro i soli elementi < 5 (chiamo la lambda con la notazione :: perchè è un metodo di un'istanza)
		System.out.println("< 5 elements:");
		values.stream().filter(n -> (n < 5)).forEach(System.out::println);
		
		// Applico una reduce, partendo da 0 e sommando tutti i valori (chiamo la lambda con la notazione :: perchè è un metodo statico)
		/* N.B.:
		 * While this may seem a more roundabout way to perform an aggregation compared to simply mutating a running total in a loop,
		 *  reduction operations parallelize more gracefully, 
		 *  without needing additional synchronization and with greatly reduced risk of data races.
		 */
		int sum = values.stream().reduce(0, Integer::sum);

		System.out.println("Sum of all elements is: " + sum);
	}
}
