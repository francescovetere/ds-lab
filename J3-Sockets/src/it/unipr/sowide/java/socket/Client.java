package it.unipr.sowide.java.socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Client {
	private static final int SPORT = 4444;
	private static final String SHOST = "localhost";

	private static final int MAX = 100;

	public void run() {
		try {
			Socket client = new Socket(SHOST, SPORT);

			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = null;

			Random r = new Random();

			while (true) {
				Request rq = new Request(r.nextInt(MAX));

				System.out.format("Client sends: %s to Server", rq.getValue());

				os.writeObject(rq);
				os.flush();

				if (is == null) {
					is = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
				}

				Object o = is.readObject();

				if (o instanceof Response) {
					Response rs = (Response) o;

					System.out.format(" and received: %s from Server%n", rs.getValue());

					if (rs.getValue() == 0) {
						break;
					}
				}
			}

			client.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		new Client().run();
	}
}
