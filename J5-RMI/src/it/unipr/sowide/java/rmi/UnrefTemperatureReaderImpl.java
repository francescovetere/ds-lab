package it.unipr.sowide.java.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.Unreferenced;
import java.util.Random;

/**
 * A remote object implementation can implement the Unreferenced interface to
 * receive notification when there are no more clients that reference that remote object
 * Its only method is unreferenced(), which is automatically called
 * This is a mechanism of explicit notification for the garbage collector
 */
public class UnrefTemperatureReaderImpl extends UnicastRemoteObject implements TemperatureReader, Unreferenced {
	private static final long serialVersionUID = 1L;

	private static final int MAX = 100;
	private static final int MIN = -100;

	private Random random;

	public UnrefTemperatureReaderImpl() throws RemoteException {
		this.random = new Random();

		// Opens logging file ...
	}

	public int getTemperature() throws RemoteException {
		int t = this.random.nextInt(MAX - MIN) + MIN;

		// Saves temperature ...

		return t;
	}

	/** 
	 * Called by the RMI runtime sometime after the runtime determines that
     * the reference list, the list of clients referencing the remote object,
     * becomes empty.
     */
	@Override
	public void unreferenced() {
		// E.g., file, network and database connections can be released
		System.out.println("Freeing resources");
	}
}
