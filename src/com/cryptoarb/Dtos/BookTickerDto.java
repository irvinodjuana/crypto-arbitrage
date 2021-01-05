package com.cryptoarb.Dtos;

public class BookTickerDto {
    private String symbol;
    private double bidPrice;
    private double bidQty;
    private double askPrice;
    private double askQty;

    public BookTickerDto(String symbol, double bidPrice, double bidQty, double askPrice, double askQty) {
        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;
    }

    public String getSymbol() {
        return symbol;
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

    @Override
    public String toString() {
        return "BookTickerDto{" +
                "symbol='" + symbol + '\'' +
                ", bidPrice=" + bidPrice +
                ", bidQty=" + bidQty +
                ", askPrice=" + askPrice +
                ", askQty=" + askQty +
                '}';
    }
}
