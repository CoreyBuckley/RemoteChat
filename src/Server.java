/* Corey Buckley
 * This program will be a server to receive messages from a client
 * using TCP/IP socket
 */
import java.io.*;
import java.net.*;

public class Server {
	private InetAddress host;
	private int port;

	public Server() {
		try {
			host = InetAddress.getLocalHost(); // use server computer as host
			port = 7777; // wait on port #7777
			System.out.println("Server name is " + host.getHostName());
			System.out.println("Server address is " + host);
			System.out.println("Port is " + port);
		} catch (UnknownHostException u) {
			u.printStackTrace();
		}
	}

	public void run() {
		try {
			ServerSocket server = new ServerSocket(port); 
			Socket conn = server.accept();
			PrintWriter pw = new PrintWriter(conn.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String sender = conn.getInetAddress().getHostName();
			System.out.println("Message is coming from " + sender);
			System.out.println("Message is: " + br.readLine());
			pw.println("Your message was received. Thank you!");
			server.close(); //close the ServerSocket
			conn.close(); //close the Socket
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server s = new Server();
		s.run();
	}
}