import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

class AuctionController extends Thread {
	private Auction item;
	private DatagramSocket socket;
	private InetAddress group;
	private DatagramPacket packet;
	private int port;

	public AuctionController(Auction resource, int port) {
		item = resource;
		this.port = port;
	}

	public void run() {
		try {
			socket = new DatagramSocket();
			group = InetAddress.getByName("224.0.0.3");
		}
		catch (SocketException e) {
			e.printStackTrace();
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}

		do{
			item.setTimer(item.getTimer() - 1);

			if(item.getTimer() == 15) {
				sendNotification("Only 15 seconds left");
			} else if (item.getTimer() == 0) {
				sendNotification(item.moveToNextItem());
				sendNotification("Moving to: " + item.getCurrentAuctionItem() + "\n");
			}

			if(item.isBidCheck()){
				sendNotification(item.getItemList().get(0).getCurrentBidder() + " has made a bid of " + item.getItemList().get(0).getItemPrice());
				item.setBidCheck(false);
			}//Checks to see if anyone has made a sucessful bid then sends it to the clients.

			try {
				sleep(1000); //Waits for one second before continuing
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while (true);
	}
	
	//Sends notifications to all clients that have joined the address of the multi cast server socket
	private void sendNotification(String message) {
			try {
				byte[] buffer = message.getBytes();
				packet = new DatagramPacket(buffer, buffer.length, group, port);
				socket.send(packet);
				Thread.sleep((long)(Math.random() * 3000));
			} catch (IOException e) {
				e.printStackTrace();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
