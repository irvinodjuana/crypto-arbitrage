package com.cryptoarb.Models;

public class BookTicker {
    private String symbol;
    private String baseAsset;
    private String quoteAsset;
    private double bidPrice;
    private double bidQty;
    private double askPrice;
    private double askQty;

    public BookTicker(String symbol, double bidPrice, double bidQty, double askPrice, double askQty) {
        this.symbol = symbol;
        this.baseAsset = null;
        this.quoteAsset = null;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getBaseAsset() {
        return baseAsset;
    }

    public void setBaseAsset(String baseAsset) {
        this.baseAsset = baseAsset;
    }

    public String getQuoteAsset() {
        return quoteAsset;
    }

    public void setQuoteAsset(String quoteAsset) {
        this.quoteAsset = quoteAsset;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public double getBidQty() {
        return bidQty;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public double getAskQty() {
        return askQty;
    }

    public boolean isActive() {
        return bidPrice > 0 && askPrice > 0 && bidQty > 0 && askQty > 0;
    }
}
