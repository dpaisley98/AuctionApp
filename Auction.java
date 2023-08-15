import java.util.ArrayList;

class Auction {
	private static ArrayList<AuctionItem> itemList;
	private static ArrayList<String> userList;
	private static int bidPeriod;
	private static int auctionTimer;
	private static boolean bidCheck;
	private AuctionItem auctionedItem;

	public Auction() {
		AuctionItem[] items =
		{new AuctionItem("Double Bed", 300, 45),
		new AuctionItem("LCD Monitor", 250, 25),
		new AuctionItem("Gnome", 30, 30),
		new AuctionItem("Roomba", 600, 30),
		new AuctionItem("Computer", 30, 30)};

		itemList = new ArrayList<>();
		userList = new ArrayList<>();
		for(int i = 0; i < items.length; i++){
			itemList.add(items[i]);
		}

		setTimer(itemList.get(0).getBiddingPeriod());
		setBidCheck(false);
	}

	public boolean addUser(String username) {
		for(String user : userList) {
			if(username.equals(user)){
				return false;
			}
		}
		userList.add(username);
		return true;
	}//Method to add username from client to the list of usernames to make sure two people don't have the same username

	public boolean addBid(int bid, String username) {
		if(bid > itemList.get(0).getItemPrice()){
			itemList.get(0).setItemPrice(bid);
			itemList.get(0).setCurrentBidder(username);
			setTimer(itemList.get(0).getBiddingPeriod());
			setBidCheck(true);
			return true;
		}
		return false;
	}

	public String moveToNextItem() {
			return checkIfItemAuctioned() ? 
							auctionedItem.getItemName() + " bought by " + auctionedItem.getCurrentBidder() 
							: "No bid was made for " + auctionedItem.getItemName();
	}

	//Checks if the bidding name isn't null, if it is then no one bid for the item and it is put back in the list
	private boolean checkIfItemAuctioned() {
		auctionedItem = new AuctionItem(itemList.get(0).getItemName(), 
										itemList.get(0).getItemPrice(), 
										itemList.get(0).getBiddingPeriod());
		auctionedItem.setCurrentBidder(itemList.get(0).getCurrentBidder());

		if(itemList.get(0).getCurrentBidder() == null){
			itemList.add(auctionedItem);
			itemList.remove(0);
			setTimer(itemList.get(0).getBiddingPeriod());
			return false;
		}

		itemList.remove(0);
		setTimer(itemList.get(0).getBiddingPeriod());

		return true;
	}

	public ArrayList<AuctionItem> getItemList() {
		return itemList;
	}

	public String getItemListString() {
		return itemList.toString();
	}

	public String getCurrentAuctionItem() {
		return itemList.get(0).toString();
	}

	public static int getBidPeriod() {
		return bidPeriod;
	}

	public static void setBidPeriod(int period) {
		bidPeriod = period;
	}

	public boolean isBidCheck() {
		return bidCheck;
	}

	public void setBidCheck(boolean check) {
		bidCheck = check;
	}

	public int getTimer() {
		return auctionTimer;
	}

	public void setTimer(int timer) {
		auctionTimer = timer;
	}
}
