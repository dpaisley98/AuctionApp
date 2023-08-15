import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class AuctionNotification implements Runnable {    
    private static MulticastSocket auctionSocket;
	private static InetAddress address;
	private static DatagramPacket packet;
    private int port;

    public AuctionNotification(int port){
        this.port = port;
    }

    public void run() {    
        try {
            //This address is used by the server to send notifications out, 
            //by joining it any notifications sent from that server will be received by this client.
            address = InetAddress.getByName("224.0.0.3");
            auctionSocket = new MulticastSocket(port);
            auctionSocket.joinGroup(address);
            while(true){
                byte[] buffer = new byte[64];
                packet = new DatagramPacket(buffer, buffer.length);
                auctionSocket.receive(packet);
                System.out.println(new String(packet.getData()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leaveGroup() {
        try {
            auctionSocket.leaveGroup(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        auctionSocket.close();
    }
} 
