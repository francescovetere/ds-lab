package it.unipr.sowide.java.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TemperatureServer {
	private static final int PORT = 1099;

	public static void main(final String[] args) throws Exception {
		// Locates the registry
		Registry registry = LocateRegistry.createRegistry(PORT);

		// Creates a Remote object
		TemperatureReader service = new TemperatureReaderImpl();
		
		// If we want, instead, to explicity notify the garbage collector:
		// TemperatureReader service = new UnrefTemperatureReaderImpl();

		// Publishes a remote reference to that object,
		// binding it to the external name “temperature”
		registry.rebind("temperature", service);
	}
}