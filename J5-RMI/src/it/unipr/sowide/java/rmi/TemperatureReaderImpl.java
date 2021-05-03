package it.unipr.sowide.java.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class TemperatureReaderImpl extends UnicastRemoteObject implements TemperatureReader {
	/**
	 * N.B.: RMI can exchange objects through serialization, 
	 * but serialized objects do not contain the code of the class they implement (see slide 25)
	 */
	private static final long serialVersionUID = 1L;

	private static final int MAX = 100;
	private static final int MIN = -100;

	private Random random;

	public TemperatureReaderImpl() throws RemoteException {
		this.random = new Random();
	}

	@Override
	public int getTemperature() throws RemoteException {
		return this.random.nextInt(MAX - MIN) + MIN;
	}
}
