package com.blakebartenbach.recordprice.thread;

import com.blakebartenbach.recordprice.JsonReader;
import com.blakebartenbach.recordprice.RecordHolder;
import com.blakebartenbach.recordprice.gson.*;

public class MarketPollingThread extends Thread {


    private RecordHolder recordHolder;
    private volatile double bitcoinCurrentPrice;
    private volatile double litecoinCurrentPrice;
    private volatile double ethereumCurrentPrice;


    public MarketPollingThread(RecordHolder recordHolder) {
        this.recordHolder = recordHolder;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Polling Market:");
            Cryptocurrency btcMarketStatus = JsonReader.getMarketStatus(" https://api.coinbase.com/v2/prices/BTC-USD/spot", BTC.class);
            Cryptocurrency ltcMarketStatus = JsonReader.getMarketStatus(" https://api.coinbase.com/v2/prices/LTC-USD/spot", LTC.class);
            Cryptocurrency ethMarketStatus = JsonReader.getMarketStatus(" https://api.coinbase.com/v2/prices/ETH-USD/spot", ETH.class);
            this.bitcoinCurrentPrice = Double.parseDouble(btcMarketStatus.getData().getAmount());
            this.litecoinCurrentPrice = Double.parseDouble(ltcMarketStatus.getData().getAmount());
            this.ethereumCurrentPrice = Double.parseDouble(ethMarketStatus.getData().getAmount());

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

    public synchronized double getBitcoinCurrentPrice() {
        return this.bitcoinCurrentPrice;
    }

    public synchronized double getLitecoinCurrentPrice() {
        return this.litecoinCurrentPrice;
    }

    public synchronized double getEthereumCurrentPrice() {
        return this.ethereumCurrentPrice;
    }

}
