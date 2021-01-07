package com.cryptoarb.Models;

import com.cryptoarb.Dtos.StreamBookTickerDto;

public class BookTicker {
    private String symbol;
    private String baseAsset;
    private String quoteAsset;
    private double bidPrice;
    private double bidQty;
    private double askPrice;
    private double askQty;

    public BookTicker(String symbol, String baseAsset, String quoteAsset, double bidPrice, double bidQty, double askPrice, double askQty) {
        this.symbol = symbol;
        this.baseAsset = baseAsset;
        this.quoteAsset = quoteAsset;
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

    public String getQuoteAsset() {
        return quoteAsset;
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

    public void setData(StreamBookTickerDto streamData) {
        bidPrice = streamData.getBidPrice();
        bidQty = streamData.getBidQty();
        askPrice = streamData.getAskPrice();
        askQty = streamData.getAskQty();
    }

    @Override
    public String toString() {
        return "BookTicker{" +
                "symbol='" + symbol + '\'' +
                ", baseAsset='" + baseAsset + '\'' +
                ", quoteAsset='" + quoteAsset + '\'' +
                ", bidPrice=" + bidPrice +
                ", bidQty=" + bidQty +
                ", askPrice=" + askPrice +
                ", askQty=" + askQty +
                '}';
    }
}
