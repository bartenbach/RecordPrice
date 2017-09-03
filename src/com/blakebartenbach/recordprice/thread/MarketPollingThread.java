package com.blakebartenbach.recordprice.thread;

import com.blakebartenbach.recordprice.JsonReader;
import com.blakebartenbach.recordprice.RecordHolder;
import com.blakebartenbach.recordprice.gson.MarketStatus;

public class MarketPollingThread implements Runnable {


    private RecordHolder recordHolder;


    public MarketPollingThread(RecordHolder recordHolder) {
        this.recordHolder = recordHolder;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Polling Market:");
            MarketStatus marketStatus = JsonReader.getMarketStatus("https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,LTC,ETH&tsyms=USD");
            Double bitcoinCurrentPrice = marketStatus.getBTC().getUSD();
            Double litecoinCurrentPrice = marketStatus.getLTC().getUSD();
            Double ethereumCurrentPrice = marketStatus.getETH().getUSD();

            System.out.println("Current Bitcoin Price: " + bitcoinCurrentPrice);
            if (bitcoinCurrentPrice > recordHolder.getBitcoinRecordPrice()) {
                System.out.println("New Bitcoin Record!");
                recordHolder.setBitcoinRecordPrice(bitcoinCurrentPrice);
            }
            System.out.println("Current Litecoin Price: " + litecoinCurrentPrice);
            if (litecoinCurrentPrice > recordHolder.getLitecoinRecordPrice()) {
                System.out.println("New Litecoin Record!");
                recordHolder.setLitecoinRecordPrice(litecoinCurrentPrice);
            }
            System.out.println("Current Ethereum Price: " + ethereumCurrentPrice);
            if (ethereumCurrentPrice > recordHolder.getEthereumRecordPrice()) {
                System.out.println("New Ethereum Record!");
                recordHolder.setEthereumRecordPrice(ethereumCurrentPrice);
            }
            // wait 1 minute before polling again..
            try {
                Thread.sleep(60000);
            } catch (InterruptedException ex) {
                System.out.println("Thread interrupted - we don't care.");
            }
        }
    }

}
