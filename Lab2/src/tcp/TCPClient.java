package tcp;

import java.io.*;
import java.net.*;

public class TCPClient {
	public static void main(String[] args) throws IOException {

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
				System.in));
		Socket clientSocket = new Socket("localhost", 6789);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));
		String sent = inFromUser.readLine();
		outToServer.writeBytes(sent + '\n');
		String received = inFromServer.readLine();
		System.out.println("Hello " + received);
		clientSocket.close();
	}

}
