package com.example;

import java.io.*;
import java.net.*;


public class AuctionServer {
	private static ServerSocket servSocket;
	private static final int PORT = 1234;

	public static void main(String[] args)
									throws IOException {
		try {
			servSocket = new ServerSocket(PORT);
		}
		catch (IOException ioEx) {
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}

		//Create an Auction object to be referenced by all the clients and the controller
		Auction auction = new Auction();

		//Create a controller to run the auction
		AuctionController controller = new AuctionController(auction, PORT);

		//Creates an admin to allow for more items to be added to the list
		AuctionAdmin admin = new AuctionAdmin(auction);

		//Start the Producer thread running...
		controller.start();
		admin.start();
		do {
			//Wait for a client to make connection...
			Socket client = servSocket.accept();
			System.out.println("\nNew client accepted.\n");

			//Create a ClientHandler thread to handle all
			//subsequent dialogue with the client, passing
			//references to both the client's socket and
			//the Auction object...
			ClientThread handler =
						new ClientThread(client, auction);

			//Start the ClientThread running...
			handler.start();
		}while (true);		//Server will run indefinitely.
	}
}
