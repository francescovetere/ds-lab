package it.unipr.sowide.java.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DataClient {
	private static final int SPORT = 4444; 				// server port
	private static final String SHOST = "localhost";	// server address

	public void send() {
		try {
			
			// Create a socket
			Socket client = new Socket(SHOST, SPORT);

			// Create communication I/O streams
			BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			DataOutputStream os = new DataOutputStream(client.getOutputStream());
			
			// Perform communication with server
			os.writeBytes("Hello\n");

			System.out.println("Client received: " + is.readLine());
			
			// Close the socket when done
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] v) {
		new DataClient().send();
	}
}
