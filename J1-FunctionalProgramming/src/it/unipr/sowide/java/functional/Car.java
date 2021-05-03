package it.unipr.sowide.java.functional;

public class Car {
	private String make;
	private String model;
	private int year;

	public Car(final String p, final String m, final int y) {
		make = p;
		model = m;
		year = y;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}
}
