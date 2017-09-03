package com.blakebartenbach.recordprice;

/**
 * This class is an object that holds the current record price for various cryptocurrencies
 */
public class RecordHolder {


    private volatile double bitcoinRecordPrice;
    private volatile double litecoinRecordPrice;
    private volatile double ethereumRecordPrice;


    /**
     * Constructor
     * @param bitcoinRecordPrice - the current bitcoin record price
     * @param litecoinRecordPrice - the current litecoin record price
     * @param ethereumRecordPrice - the current ethereum record price
     */
    public RecordHolder(double bitcoinRecordPrice, double litecoinRecordPrice, double ethereumRecordPrice) {
        this.bitcoinRecordPrice = bitcoinRecordPrice;
        this.litecoinRecordPrice = litecoinRecordPrice;
        this.ethereumRecordPrice = ethereumRecordPrice;
    }

    /**
     * Retrieves the current bitcoin record price
     * @return - double - the current bitcoin record price
     */
    public double getBitcoinRecordPrice() {
        return this.bitcoinRecordPrice;
    }

    /**
     * Retrieves the current ethereum record price
     * @return - double - the current ethereum record price
     */
    public double getEthereumRecordPrice() {
        return ethereumRecordPrice;
    }

    /**
     * Retrieves the current litecoin record price
     * @return - double - the current litecoin record price
     */
    public double getLitecoinRecordPrice() {
        return litecoinRecordPrice;
    }

    public void setBitcoinRecordPrice(double price) {
        this.bitcoinRecordPrice = price;
    }

    public void setLitecoinRecordPrice(double price) {
        this.litecoinRecordPrice = price;
    }

    public void setEthereumRecordPrice(double price) {
        this.ethereumRecordPrice = price;
    }
}
