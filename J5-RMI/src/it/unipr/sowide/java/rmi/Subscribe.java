package it.unipr.sowide.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Subscribe extends Remote {
  void subscribe(final TemperatureWriter w) throws RemoteException;
}
