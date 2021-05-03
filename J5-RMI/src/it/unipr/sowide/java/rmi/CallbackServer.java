package it.unipr.sowide.java.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class CallbackServer {
	private static final int PORT = 1099;
	private static final int MAX = 100;
	private static final int MIN = -100;

	public static void main(final String[] args) throws Exception {
		Random random = new Random();
		Registry registry = LocateRegistry.createRegistry(PORT);

		Set<TemperatureWriter> writers = new CopyOnWriteArraySet<>();
		
		// Creates a remote object
		Subscribe service = new SubscribeImpl(writers);
		
		// Publishes a remote reference to that object with external name “subscribe”
		registry.rebind("subscribe", service);

		while (true) {
			int t = random.nextInt(MAX - MIN) + MIN;

			try {
				// For every subscribed client
				for (TemperatureWriter w : writers) {
					System.out.println("ok");
					// Invokes the remote method on the client
					w.putTemperature(t);
				}

				Thread.sleep(1000);
			} catch (Exception e) {
				continue;
			}
		}
	}
}
