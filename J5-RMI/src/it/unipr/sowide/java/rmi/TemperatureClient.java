package it.unipr.sowide.java.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * The {@code TemperatureClient} class provides an implementation of a RMI
 * client that requires a service to a RMI server.
 *
 **/

public class TemperatureClient {
	public static void main(final String[] args) throws Exception {
		// Locates the registry
		Registry registry = LocateRegistry.getRegistry();
		
		// Looks up a reference to a remote object with external name “temperature”
		TemperatureReader service = (TemperatureReader) registry.lookup("temperature");
		
		// Invokes the remote method on the server
		System.out.println("Temperature is " + service.getTemperature());
	}
}
