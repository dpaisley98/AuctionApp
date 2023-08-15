# Auction App Java Program

![Java Logo](https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/200px-Java_programming_language_logo.svg.png)

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Architecture](#architecture)

## Introduction

The Auction Simulation Java Program is a client-server application that simulates an auction taking place. It allows multiple clients to participate in bidding for items and provides real-time updates on the highest bid. This program demonstrates the principles of client-server communication, multi-threading, and network programming in Java.

## Features

- **Client-Server Interaction**: Clients can connect to the server and participate in the auction by placing bids on items.

- **Real-time Updates**: The server broadcasts real-time updates to all connected clients whenever a new bid is placed.

- **Item Listing**: The server lists available items for bidding, including their descriptions and starting bid prices.

- **Bidding Logic**: Clients can place bids on items, and the server ensures that the bid is valid and updates the current highest bid.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later installed.
- Basic understanding of Java programming and networking concepts.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/dpaisley98/auctionapp.git

2. Head to folder and run bat files.

    Run the server.bat first then you can run the client.bat file as many times so they can connect to the auction.


## Architecture

The auction simulation program follows a client-server architecture:

- **Server**: Manages the auction process, handles client connections, and updates the bidding status in real-time.

- **Client**: Connects to the server, interacts with the user, and sends bid requests to the server.

The communication between the client and server is achieved using Java's `Socket` and `ServerSocket` classes, allowing for bidirectional data flow.



   
