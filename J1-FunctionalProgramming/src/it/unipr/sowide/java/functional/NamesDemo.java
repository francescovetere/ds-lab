package it.unipr.sowide.java.functional;

import java.util.Arrays;
import java.util.List;

public final class NamesDemo {
	private NamesDemo() {
	}

	public static void main(final String[] args) {
		List<String> names = Arrays.asList("Jake", "Raju", "Kim", "Kara", "Paul", "Brad", "Mike");

		System.out.println("Found a 3 letter name?: " + names.stream().anyMatch(name -> name.length() == 3));

		System.out.println("Found Kim?: " + names.stream().anyMatch(name -> name.contains("Kim")));

		names.stream().filter(name -> name.length() == 4).forEach(System.out::println);

		names.stream().filter(name -> name.length() == 4).map(name -> name.toUpperCase()).forEach(System.out::println);
	}
}
