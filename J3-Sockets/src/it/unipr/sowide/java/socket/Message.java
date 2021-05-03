package it.unipr.sowide.java.socket;

import java.io.Serializable;

// Interfaccia Serializable permette di creare oggetti persistenti, ovvero che persistono anche dopo lo spegnimento della JVM
// Tutti i campi devono essere tipi primitivi
// Se un campo è un oggetto, deve essere a sua volte di una classe che implementa Serializable
// L'oggetto viene convertito in una serie di byte, utile quando si deve inviare in rete
// Alla destinazione, verrà deserializzato
public final class Message implements Serializable { 
	private static final long serialVersionUID = 1L;

	private User user;
	private String content;

	public Message(final User u, final String c) {
		this.user = u;
		this.content = c;
	}

	public User getUser() {
		return this.user;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(final String c) {
		this.content = c;
	}
}
