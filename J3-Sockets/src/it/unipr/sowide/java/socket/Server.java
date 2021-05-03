package it.unipr.sowide.java.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
	private static final int COREPOOL = 5;
	private static final int MAXPOOL = 100;
	private static final long IDLETIME = 5000;

	private static final int SPORT = 4444;

	private ServerSocket socket;
	private ThreadPoolExecutor pool;

	public Server() throws IOException {
		this.socket = new ServerSocket(SPORT);
	}

	private void run() {
		this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

		while (true) {
			try {
				Socket s = this.socket.accept();
				
				// La socket s serve solamente per accettare nuove connessioni
				// Queste vengono poi smistate in un nuovo thread a parte, nel seguente modo
				
				this.pool.execute(new ServerThread(this, s));
			} catch (Exception e) {
				break;
			}
		}

		this.pool.shutdown();
	}

	public ThreadPoolExecutor getPool() {
		return this.pool;
	}

	public void close() {
		try {
			// Grazie a questa close, vado direttamente a this.pool.shutdown()
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) throws IOException {
		new Server().run();
	}
}
