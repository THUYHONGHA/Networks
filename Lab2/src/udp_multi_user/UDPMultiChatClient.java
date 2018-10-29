package udp_multi_user;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPMultiChatClient {
	public static void main(String[] args) throws IOException {

		while (true) {
			// get input
			System.out.println("Message:");
			Scanner sc = new Scanner(System.in);
			String message = sc.next();

			// get a datagram socket
			DatagramSocket socket = new DatagramSocket();

			// send request
			byte[] buf = new byte[256];
			buf = message.getBytes();

			InetAddress address = InetAddress.getLocalHost();
			DatagramPacket packet = new DatagramPacket(buf, buf.length, address, Integer.parseInt("9090"));
			socket.send(packet);

			// get response
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

			// display response
			String received = new String(packet.getData(), 0, packet.getLength());
			System.out.println(packet.getSocketAddress() + " " + received);

		}
	}
}
