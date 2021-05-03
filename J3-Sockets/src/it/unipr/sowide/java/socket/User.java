package it.unipr.sowide.java.socket;

import java.io.Serializable;

public final class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private final String firstName;
	private final String lastName;
	private final String address;
	private final transient String password; // Dico al compilatore che questa variabile non deve essere serializzata, viene messa a null
	
	public User(final String f, final String l, final String a, final String p) {
		this.firstName = f;
		this.lastName = l;
		this.address = a;
		this.password = p;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getAddress() {
		return this.address;
	}

	public String getPassword() {
		return this.password;
	}
}
