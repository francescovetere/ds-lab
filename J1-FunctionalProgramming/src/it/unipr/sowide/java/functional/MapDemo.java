package it.unipr.sowide.java.functional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class MapDemo {
	private MapDemo() {
	}

	private static <K, V> void printMap(final String n, final Map<K, V> l) {
		for (Entry<K, V> e : l.entrySet()) {
			System.out.println(n + " element key " + e.getKey() + " value " + e.getValue());
		}

		System.out.println();
	}

	public static void main(final String[] args) {
		// Creo una mappa immutabile, tramite il metodo of
		Map<String, Integer> immutable = Map.of("John", 1, "Steve", 2, "Jack", 3);
		printMap("immutable", immutable);
		
		// Creo una mappa modificabile, tramite costruttore HashMap e successive put
		Map<String, Integer> keyVals = new HashMap<>(immutable);
		
		keyVals.put("Mary", 4);
		keyVals.remove("Bob"); // ritorna null, in quanto la chiave "Bob" non esiste

		printMap("KeyVals", keyVals);

		// Creo una mappa modificabile, tramite costruttore TreeMap e successive put
		keyVals = new TreeMap<>();

		keyVals.put("John", 1); // la chiave "John" esisteva gia': rimpiazza il suo valore con 1
		keyVals.put("Mary", 2); // la chiave "Mary" esisteva gia': rimpiazza il suo valore con 2
		keyVals.put("bob", 3); // la chiave "bob" e' invece nuova

		printMap("keyVals", keyVals);
		
		// Creo un'interfaccia funzionale per confrontare due stringhe
		// Il suo valore lo associo a una lambda che fa ritorna:
		// 0 se uguali, -1 se x > y, +1 se x < y
		// N.B.: Un'interfaccia funzionale ha sempre 1 solo metodo: posso evitare di scrivere l'anonymous inner type completo
		Comparator<String> myComparator = (x, y) -> {
			if (x.equals(y)) {
				return 0;
			} else if (x.compareTo(y) > 0) {
				return -1;
			}

			return 1;
		};
		
		// Creo una nuova mappa modificabile in cui le varie put verranno ordinate secondo myComparator
		// Ovvero, ogni inserimento sarà ordinato lessicograficamente in base alla chiave
		keyVals = new TreeMap<>(myComparator);

		keyVals.put("John", 1);
		keyVals.put("Mary", 2);
		keyVals.put("bob", 3); // Prima "bob", poi "Mary", poi "John"

		printMap("keyVals", keyVals);
	}
}
