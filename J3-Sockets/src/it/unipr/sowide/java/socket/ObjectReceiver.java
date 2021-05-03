package it.unipr.sowide.java.socket;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ObjectReceiver {
	private static final String ADDRESS = "230.0.0.1";
	private static final int DPORT = 4446;
	private static final int SIZE = 1024;

	public void receive() {
		try {
			MulticastSocket socket = new MulticastSocket(DPORT);
			socket.joinGroup(InetAddress.getByName(ADDRESS));

			byte[] buf = new byte[SIZE];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);

			socket.receive(packet);

			Object o = toObject(packet.getData());

			if (o instanceof Message) {
				Message m = (Message) o;

				System.out.format("Receiver received: %s from user with password %s\n", m.getContent(),
						m.getUser().getPassword());
				 // N.B.: getPassword() sarà null, in quanto il campo password era transient,
				 // 	  e dunque non viene serializzato!
			}

			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Object toObject(final byte[] b) throws IOException, ClassNotFoundException {
		// Gli streams si possono concatenare tra loro
		
		ObjectInputStream s = new ObjectInputStream(new ByteArrayInputStream(b));

		Object o = s.readObject();
		s.close();

		return o;
	}

	public static void main(final String[] v) {
		new ObjectReceiver().receive();
	}
}
