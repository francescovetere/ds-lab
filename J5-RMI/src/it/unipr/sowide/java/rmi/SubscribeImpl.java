package it.unipr.sowide.java.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

public class SubscribeImpl extends UnicastRemoteObject implements Subscribe {
  private static final long serialVersionUID = 1L;

  private Set<TemperatureWriter> writers;

  public SubscribeImpl(final Set<TemperatureWriter> s) throws RemoteException {
    this.writers = s;
  }

  @Override
  public void subscribe(final TemperatureWriter w) throws RemoteException {
    this.writers.add(w);
  }
}
