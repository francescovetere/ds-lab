package it.unipr.sowide.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TemperatureWriter extends Remote {
  void putTemperature(final int t) throws RemoteException;
}
