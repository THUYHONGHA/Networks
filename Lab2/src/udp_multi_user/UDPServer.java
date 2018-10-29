package udp_multi_user;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class UDPServer {
	static DatagramSocket socket;
	static DatagramPacket packet;
	static ArrayList<InetAddress> arrayList;
	static LinkedBlockingQueue<byte[]> messages;

	public static void main(String[] args) throws IOException {

		arrayList = new ArrayList<InetAddress>();

		messages = new LinkedBlockingQueue<byte[]>();

		byte[] b = new byte[500];

		System.out.println("Server initialized..");

		socket = new DatagramSocket(Integer.parseInt("9090"));
		packet = new DatagramPacket(b, b.length);

		Thread acceptorThread = new Thread() {
			public void run() {
				while (true) {
					try {
						socket.receive(packet);

						System.out.println("acceptor");
						if (arrayList.add(packet.getAddress()))
							System.out.println("listtrue");

						if (messages.add(packet.getData()))
							System.out.println("queuetrue");
						;
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		};

		acceptorThread.start();

		Thread messageHandler = new Thread() {
			public void run() {

				System.out.println("handler");
				while (true) {
					if (messages.poll() != null)

					{
						byte[] b = "soham".getBytes();
						sendAll(b);
					}

				}
			}
		};

		messageHandler.start();

	}

	public static void sendAll(byte[] b) {
		for (InetAddress address : arrayList) {
			DatagramPacket packet = new DatagramPacket(b, b.length, address, 4445);
			try {
				socket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		socket.close();
	}
}
