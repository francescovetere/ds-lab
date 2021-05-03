package it.unipr.sowide.java.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TemperatureWriterImpl
  extends UnicastRemoteObject implements TemperatureWriter {
  private static final long serialVersionUID = 1L;

  public TemperatureWriterImpl() throws RemoteException {
  }

  public void putTemperature(final int t) throws RemoteException {
    System.out.println(t);
  }
}
