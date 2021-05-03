package it.unipr.sowide.java.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server non ha bisogno dell'indirizzo IP, perchè è un'entità passiva, che reagisce a richieste
 */
public class DataServer {
	private static final int SPORT = 4444; // server port

	public void reply() {
		try {
			// Create the server socket
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(SPORT);
			Socket client;
			
			while(true) {
				System.out.println();
				System.out.println("Waiting for a connection...");
	
				// Wait for the client request (metodo bloccante)
				client = server.accept();
				
				System.out.println("Connection request from " + client.getInetAddress() + ":" + client.getPort());
				
				// Create I/O communication streams
				// Input stream per leggere messaggi (client.getInputStream)
				// output stream per scriverli (client.getOutputStream)
				BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
				DataOutputStream os = new DataOutputStream(client.getOutputStream());
				
				// Perform communication with client
				// Legge una linea dallo stream di input
				System.out.println("Server received: " + is.readLine());
				
				// Scrive sullo stream di output
				os.writeBytes("Done\n");
			}
			
			// Close sockets
//			client.close();
//			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] v) {
		new DataServer().reply();
	}
}
