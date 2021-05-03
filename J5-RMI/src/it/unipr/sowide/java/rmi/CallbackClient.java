package it.unipr.sowide.java.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CallbackClient {
	public static void main(final String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry();

		// Client creates a remote object
		// (?) Come mai main thread del client non termina?
		// ==> distributed garbage collection: server sa che client sta usando l'oggetto
		TemperatureWriter w = new TemperatureWriterImpl();
		
		// Looks up a reference to a remote object with external name “subscribe”
		Subscribe service = (Subscribe) registry.lookup("subscribe");

		// Client passes the remote object reference to the server
		// Whenever an event occurs, the server calls the client via the remote object
		service.subscribe(w);
	}
}
