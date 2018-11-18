package udp;

import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

@SuppressWarnings("resource")
public class PingTask extends TimerTask {

	private String host;
	private int port;
	private int number;

	public PingTask(String host, int port, int number) {
		this.host = host;
		this.port = port;
		this.number = number;
	}

	@Override
	public void run() {
		try {
			DatagramSocket socket = new DatagramSocket();
			InetAddress loc = InetAddress.getByName(host);
			String strSend = "PING";
			byte[] buf = new byte[1024];
			DatagramPacket dpReceive = new DatagramPacket(buf, 1024);
			DatagramPacket dpSend = new DatagramPacket(strSend.getBytes(), strSend.length(), loc, port);
			socket.setSoTimeout(1000);
			socket.send(dpSend);
			Date date = new Date();
			try {
				socket.receive(dpReceive);
				DateFormat format = new SimpleDateFormat("HH:mm:ss");
				System.out.print("PING " + Integer.toString(this.number) + " " + format.format(date) + "\n");

			} catch (InterruptedIOException e) {
				System.out.println("Error:"+e);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}