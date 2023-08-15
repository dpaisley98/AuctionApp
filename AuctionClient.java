import java.io.*;
import java.net.*;
import java.util.*;

public class AuctionClient {
	private static InetAddress host;
	private static final int PORT = 1234;

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		try{
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException uhEx) {
			System.out.println("\nHost ID not found!\n");
			System.exit(1);
		}
		sendMessages();
	}

	private static void sendMessages() throws ClassNotFoundException{
		Socket socket = null;
		Thread notificationThread;
		AuctionNotification auctionNotification = null;

		try {
			socket = new Socket(host,PORT);
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			Scanner userEntry = new Scanner(System.in);
			String message;
			boolean joinCheck = false;
			boolean userCheck = false;
			auctionNotification = new AuctionNotification(PORT);
			notificationThread = new Thread(auctionNotification); 
			do {
				System.out.println("Enter a username");
				String username = userEntry.nextLine();
				output.writeUTF(username);
				output.flush();
				userCheck = input.readBoolean();
			}while(!userCheck); //Loops through indefinitely until valid username is given
			do {
				System.out.println(auctionMenu(joinCheck));
				message =  userEntry.nextLine();
				switch (message) {
					case "1":
						output.writeUTF(message);
						output.flush();
						Object item = input.readObject();
						System.out.println("AUCTION> " + item);
						if(!joinCheck) {
							joinCheck = true;
							notificationThread.start();
						}//If already joined won't run this code, otherwise will join the multicast address for notifications
						break;
					case "2":
						System.out.println("Enter a number to bid");
						String bid = userEntry.nextLine();
						String bidMessage = message + "-" + bid;
						output.writeUTF(bidMessage);
						output.flush();
						System.out.println("AUCTION> " + input.readUTF());
						break;
					case "3":
						output.writeUTF(message);
						output.flush();
						Object items = input.readObject();
						System.out.println("AUCTION> " + items);
						break;
					case "4":
						output.writeUTF(message);
						output.flush();
						break;
					default:
						break;
				}
			}while (!message.equals("4"));
			userEntry.close();
		}
		catch(IOException ioEx) {
			ioEx.printStackTrace();
		}

		finally {
			try {
				System.out.println("\nClosing connection...");
				socket.close();
				auctionNotification.shutdown();
			}
			catch(IOException ioEx) {
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}

	private static String auctionMenu(boolean check) {
		String joinChoice = check ? "Show item up for Auction" : "Join Auction";
		return "\n\nPlease enter the number corrosponding to the option you want\n" +
		"1. " + joinChoice + "\n" + 
		"2. Make Bid\n" +
		"3. List all items up for Auction\n" +
		"4. Leave Auction\n\n";
	}
}
