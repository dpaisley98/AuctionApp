package com.example;

public class AuctionItem {
    private String itemName;
    private int itemPrice;
    private String currentBidder;
    private int biddingPeriod;


    public AuctionItem(String itemName, int itemPrice, int biddingPeriod) {
        setItemName(itemName);
        setItemPrice(itemPrice);
        setBiddingPeriod(biddingPeriod);
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getCurrentBidder() {
        return this.currentBidder;
    }

    public void setCurrentBidder(String currentBidder) {
        this.currentBidder = currentBidder;
    }

    public int getBiddingPeriod() {
        return this.biddingPeriod;
    }

    public void setBiddingPeriod(int biddingPeriod) {
        if(biddingPeriod <= 45) {
            this.biddingPeriod = biddingPeriod;
        }else {
            throw new Error("Must be less than or equal to 45 seconds");
        }
    }

    //toString method used to print out the values of the object without having to go through each field one by one.
    @Override
    public String toString() {
        return
            " Item Name: " + getItemName() + "," +
            " Item Price: " + itemPrice + ", " +
            " Current Top Bidder: " + getCurrentBidder() +
            "}\n";
    }
}
