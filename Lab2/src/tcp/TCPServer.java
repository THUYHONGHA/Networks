package tcp;

import java.io.*;
import java.net.*;

public class TCPServer {
	public static void main(String[] args) throws IOException {

		ServerSocket welcomeSocket = new ServerSocket(6789, 0,
				InetAddress.getByName("localhost"));

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());
			String received = inFromClient.readLine();
			System.out.println("Hello " + received);
			outToClient.writeBytes(received+"\n");
		}
	}

}
