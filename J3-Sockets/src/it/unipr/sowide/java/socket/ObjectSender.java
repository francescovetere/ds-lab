package it.unipr.sowide.java.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ObjectSender {
	private static final int OPORT = 4440;
	private static final String ADDRESS = "230.0.0.1";
	private static final int DPORT = 4446;

	public void send() {
		try {
			DatagramSocket socket = new DatagramSocket(OPORT);

			InetAddress group = InetAddress.getByName(ADDRESS);

			Message m = new Message(new User("Agostino", "Poggi", "agostino.poggi@unipr.it", "agostino"), "hello");

			System.out.format("Sender sends %s for user with password %s\n", m.getContent(), m.getUser().getPassword());

			byte[] buf = toByteArray(m);
			
			DatagramPacket packet = new DatagramPacket(buf, buf.length, group, DPORT);
			socket.send(packet);
			
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] toByteArray(final Object o) throws IOException {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream s = new ObjectOutputStream(b);

		s.writeObject(o);
		s.flush();
		s.close();
		b.close();

		return b.toByteArray();
	}

	public static void main(final String[] v) {
		new ObjectSender().send();
	}
}
