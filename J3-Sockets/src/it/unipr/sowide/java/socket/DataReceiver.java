package it.unipr.sowide.java.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class DataReceiver {
	private static final String ADDRESS = "230.0.0.1";
	private static final int DPORT = 4446;
	private static final int SIZE = 256;

	public void receive() {
		try {
			
			System.out.println("Waiting for a new connection...");
			
			/** Deprecated! **/
			// Create a multicast socket...
			// MulticastSocket socket = new MulticastSocket(DPORT);
			
			// ...and join the group (?) Why?
			
			// socket.joinGroup(InetAddress.getByName(ADDRESS));
			
			// Nuovo modo per fare il joinGroup (è stato introdotto per permettere di avere un gruppo su più protocolli, ma spesso è IP)
			InetAddress inetA = InetAddress.getByName(ADDRESS);
			InetSocketAddress group = new InetSocketAddress(inetA, DPORT);
			NetworkInterface netI = NetworkInterface.getByInetAddress(inetA);
			MulticastSocket socket = new MulticastSocket(DPORT);
			
			// Uso un oggettto group di tipo InetSocketAddress, ossia un gruppo sul protocollo IP
			socket.joinGroup(group, netI);
			
			// Build a datagram packet to be received
			// SIZE deve essere sufficientemente largo! Devo essere sicuro che il sender mi invia al max SIZE bytes
			byte[] buf = new byte[SIZE];
			DatagramPacket packet = new DatagramPacket(buf, buf.length);

			// Receive the datagram packet
			socket.receive(packet);

			System.out.println("Receiver received: " + new String(packet.getData()));

			// Close the socket when done
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] v) {
		new DataReceiver().receive();
	}
}
