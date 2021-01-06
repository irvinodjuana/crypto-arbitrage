package com.cryptoarb.Dtos;

import com.google.gson.annotations.SerializedName;

public class StreamBookTickerDto {
    @SerializedName("u") private long updateId;
    @SerializedName("s") private String symbol;
    @SerializedName("b") private double bidPrice;
    @SerializedName("B") private double bidQty;
    @SerializedName("a") private double askPrice;
    @SerializedName("A") private double askQty;

    public StreamBookTickerDto(long updateId, String symbol, double bidPrice, double bidQty, double askPrice, double askQty) {
        this.updateId = updateId;
        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;
    }

    public long getUpdateId() {
        return updateId;
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
        return "StreamBookTickerDto{" +
                "updateId=" + updateId +
                ", symbol='" + symbol + '\'' +
                ", bidPrice='" + bidPrice + '\'' +
                ", bidQty='" + bidQty + '\'' +
                ", askPrice='" + askPrice + '\'' +
                ", askQty='" + askQty + '\'' +
                '}';
    }
}
