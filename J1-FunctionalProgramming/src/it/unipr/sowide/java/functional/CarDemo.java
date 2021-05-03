package it.unipr.sowide.java.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class CarDemo {
	private CarDemo() {
	}

	private static List<Car> carsSortedByYear = new ArrayList<>();

	public static List<String> impFilter(final List<Car> cars) {
		for (Car car : cars) {
			if (car.getYear() > 2000) {
				carsSortedByYear.add(car);
			}
		}

		Collections.sort(carsSortedByYear, (x, y) -> Integer.valueOf(x.getYear()).compareTo(y.getYear()));

		List<String> models = new ArrayList<>();

		for (Car car : carsSortedByYear) {
			models.add(car.getModel());
		}

		return models;
	}

	public static List<String> funFilter(final List<Car> cars) {
		return cars.stream().filter(car -> car.getYear() > 2000).sorted(Comparator.comparing(Car::getYear))
				.map(Car::getModel).collect(Collectors.toList());
	}

	public static void main(final String[] args) {
		// Creo una lista di Car
		List<Car> l = Arrays.asList(new Car("Jeep", "Wrangler", 2011), new Car("Jeep", "Comanche", 1990),
				new Car("Dodge", "Avenger", 2010), new Car("Buick", "Cascada", 2016), new Car("Ford", "Focus", 2012),
				new Car("Chevrolet", "Geo Metro", 1992));
		
		// Filtro le sole auto con year > 2000, e ne ritorno la lista dei modelli
		
		// Modo 1: ciclo for manuale
		System.out.println("Modo 1");
		impFilter(l).forEach(System.out::println);
		
		// Modo 2: stream()
		System.out.println("\nModo 2");
		funFilter(l).forEach(System.out::println);
	}
}
