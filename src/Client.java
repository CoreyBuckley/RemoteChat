/* Corey Buckley
 * This program will be a client to send messages to a server
 */
import java.io.*;
import java.net.*;

public class Client {
	private int port;
	private InetAddress host;

	public Client() {
		try {
			port = 7777; // use port #7777 that server is waiting on
			String name = InetAddress.getLocalHost().getHostName(); 
			host = InetAddress.getByName(name); //use getByName
		} catch (UnknownHostException u) {
			u.printStackTrace();
		}
	}

	public void run(String mes) {
		try {
			Socket client = new Socket(host, port); //create a connection, or a socket, to the server
			PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			pw.println(mes); //Send the message to the server
			System.out.println(br.readLine()); //read any output from the server; expected: "Your message was received. Thank you!"
			client.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter a message:  ");
		line = br.readLine();

		Client c = new Client();
		c.run(line); // call run method with string line to send to server
	}
}