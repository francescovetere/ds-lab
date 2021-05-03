package it.unipr.sowide.java.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DataSender {
	private static final int OPORT = 4440;
	private static final String ADDRESS = "230.0.0.1";
	private static final int DPORT = 4446;

	public void send() {
		try {
			// Create a datagram socket and group address
			DatagramSocket socket = new DatagramSocket(OPORT);
			InetAddress group = InetAddress.getByName(ADDRESS);
			
			// Build a datagram packet to be sent
			String s = "Hello\n";
			byte[] b = s.getBytes();
			
			DatagramPacket packet = new DatagramPacket(b, b.length, group, DPORT);
			
			// Send the datagram packet
			System.out.println("Sending " + s);
			socket.send(packet);
			
			// Close the socket when done
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] v) {
		new DataSender().send();
	}
}
