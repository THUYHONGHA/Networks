package udp;

import java.util.Timer;
import java.util.TimerTask;

public class PingClient {

	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = 1234;

		Timer timer = new Timer(true);
		int times = 10;
		for (int i = 0; i < times; i++) {
			TimerTask task = new PingTask(host, port, i);
			timer.schedule(task, 1000 * i);
		}

		Thread.sleep(12000);
	}

}
