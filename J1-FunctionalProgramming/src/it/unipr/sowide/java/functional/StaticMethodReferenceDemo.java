package it.unipr.sowide.java.functional;

import java.util.Random;

public final class StaticMethodReferenceDemo {
	private StaticMethodReferenceDemo() {
	}

	private static boolean numCheck(final IntPredicate p, final int n) {
		return p.check(n);
	}

	public static void main(final String[] args) {
		final int max = 50;

		Random r = new Random();
		
		// Genero numero random
		int num = r.nextInt(max) - max / 2;
		
		// Dichiaro l'interfaccia funzionale che userò (che controlla se numero è pari)
		// N.B.: Un'interfaccia funzionale ha sempre 1 solo metodo: posso evitare di scrivere l'anonymous inner type completo, ovvero:
		/* IntPredicate intPredicate = new IntPredicate() {
			
			@Override
			public boolean check(int number) {
				return (number % 2) == 0;
			}
		};
		*/
		// Uso invece la seguente notazione piu' compatta
		 IntPredicate intPredicate = number -> (number % 2) == 0; // Uso questa notazione sempre, anche negli altri esercizi del workspace
		
		// Modo 0: uso un metodo statico di questa classe
		System.out.println("Lambda expression: " + num + " is even: " + numCheck(intPredicate, num));
		// Modo 1: uso un metodo statico di un'altra classe
		System.out.println(
				"Static method reference: " + num + " is even: " + numCheck(IntPredicatesChecker::isEven, num));
		
		
		// Ora invece uso una nuova interfaccia funzionale (che controlla se numero è positivo)
		intPredicate = number -> number > 0;
		
		// Modo 0: uso un metodo statico di questa classe
		System.out.println("Lambda expression: " + num + " is positive: " + numCheck(intPredicate, num));
		// Modo 1: uso un metodo statico di un'altra classe
		System.out.println(
				"Static method reference: " + num + " is positive: " + numCheck(IntPredicatesChecker::isPositive, num));
	}
}
