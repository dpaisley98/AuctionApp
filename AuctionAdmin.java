import java.util.Scanner;

public class AuctionAdmin extends Thread{
	private Auction item;

    public AuctionAdmin(Auction resource) {
		item = resource;
	}

	public void run() {
        System.out.println(adminMenu()); 
        //Prints the menu outside the loop and inside the add method to prevent the 
        //server from printing it twice as the scanner may not have been flushed 
        //before the next loop through the while loop
        try (Scanner userEntry = new Scanner(System.in)) {
            do {
                String userInput = userEntry.nextLine();
                switch (userInput) {
                    case "1":
                        System.out.println("\nItem Name: ");
                        String itemName = userEntry.nextLine();
                        System.out.println("\nItem Price: ");
                        int itemPrice = userEntry.nextInt();
                        System.out.println("\nBidding Period: ");
                        int biddingPeriod = userEntry.nextInt();
                        addItem(itemName, itemPrice, biddingPeriod);
                        break;
                    default:
                        break;
                }
            }while (true);
        }
	}

    private void addItem(String itemName, int itemPrice, int biddingPeriod){
        try {
            item.getItemList().add(new AuctionItem(itemName, itemPrice, biddingPeriod));
            Thread.sleep((long)(Math.random() * 1000)); 
            System.out.println(adminMenu());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Error e) { //Catches error thrown if the value for the bidding period exceeds 45 seconds
            System.out.println(e.toString());
        }
    }//Method to add an item to the list of items

    private static String adminMenu() {
		return "\n\nPlease enter the number corrosponding to the option you want\n" +
		"1. Add item\n";
	}//String to display the options available to the admin of the server
}
