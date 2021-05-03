package it.unipr.sowide.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota, perchè estende Remote
 */
public interface TemperatureReader extends Remote {
	int getTemperature() throws RemoteException;
}
