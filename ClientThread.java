import java.io.*;
import java.net.*;

class ClientThread extends Thread {
   private Socket client;
   private Auction item;
   private ObjectInputStream input;
   private ObjectOutputStream output;
   private String username;


   public ClientThread(Socket socket, Auction resource) {
      client = socket;
      item = resource;

      try {
         //Create input and output streams on the socket...
		output = new ObjectOutputStream(client.getOutputStream());
		input = new ObjectInputStream(client.getInputStream());

      }
      catch(IOException ioEx) {
         ioEx.printStackTrace();
      }
   }

	public void run() {
		String request = "";
		try {
			boolean userCheck = false;
			do {
				username = input.readUTF();
				userCheck = item.addUser(username);
				output.writeBoolean(userCheck);
				output.flush();
			}while(!userCheck);

			do {
				request = input.readUTF();

				switch (request) {
					case "1":
						output.writeObject(item.getCurrentAuctionItem());
						break;
					case "3":
						output.writeObject(item.getItemListString());
						break;
					default:
						if(request.matches("^2\\-\\d+$")) {
							int bidAmount = Integer.parseInt(request.substring(request.indexOf("-") + 1));
							boolean bidCheck = item.addBid(bidAmount, username);
							output.writeUTF(bidCheck ? "Success" : "Please enter a bid higher than: " + item.getItemList().get(0).getItemPrice());
							output.flush();
						}
						break;
				}
			}while (!request.equals("4"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		try {
			System.out.println(
						"Closing down connection...");
			client.close();
		}
		catch(IOException ioEx) { 
			System.out.println(
				"Unable to close connection to client!");;
		}
	}
}
