package com.cryptoarb.Dtos;

import java.util.Arrays;

public class SymbolDto {
    private String symbol;
    private String status;
    private String baseAsset;
    private int baseAssetPrecision;
    private String quoteAsset;
    private int quoteAssetPrecision;
    private String[] orderTypes;
    private boolean icebergAllowed;
    private boolean ocoAllowed;
    private boolean isSpotTradingAllowed;
    private boolean isMarginTradingAllowed;

    public SymbolDto(String symbol,
                     String status,
                     String baseAsset,
                     int baseAssetPrecision,
                     String quoteAsset,
                     int quoteAssetPrecision,
                     String[] orderTypes,
                     boolean icebergAllowed,
                     boolean ocoAllowed,
                     boolean isSpotTradingAllowed,
                     boolean isMarginTradingAllowed) {
        this.symbol = symbol;
        this.status = status;
        this.baseAsset = baseAsset;
        this.baseAssetPrecision = baseAssetPrecision;
        this.quoteAsset = quoteAsset;
        this.quoteAssetPrecision = quoteAssetPrecision;
        this.orderTypes = orderTypes;
        this.icebergAllowed = icebergAllowed;
        this.ocoAllowed = ocoAllowed;
        this.isSpotTradingAllowed = isSpotTradingAllowed;
        this.isMarginTradingAllowed = isMarginTradingAllowed;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getStatus() {
        return status;
    }

    public String getBaseAsset() {
        return baseAsset;
    }

    public int getBaseAssetPrecision() {
        return baseAssetPrecision;
    }

    public String getQuoteAsset() {
        return quoteAsset;
    }

    public int getQuoteAssetPrecision() {
        return quoteAssetPrecision;
    }

    public String[] getOrderTypes() {
        return orderTypes;
    }

    public boolean isIcebergAllowed() {
        return icebergAllowed;
    }

    public boolean isOcoAllowed() {
        return ocoAllowed;
    }

    public boolean isSpotTradingAllowed() {
        return isSpotTradingAllowed;
    }

    public boolean isMarginTradingAllowed() {
        return isMarginTradingAllowed;
    }

    @Override
    public String toString() {
        return "SymbolDto{" +
                "symbol='" + symbol + '\'' +
                ", status='" + status + '\'' +
                ", baseAsset='" + baseAsset + '\'' +
                ", baseAssetPrecision=" + baseAssetPrecision +
                ", quoteAsset='" + quoteAsset + '\'' +
                ", quoteAssetPrecision=" + quoteAssetPrecision +
                ", orderTypes=" + Arrays.toString(orderTypes) +
                ", icebergAllowed=" + icebergAllowed +
                ", ocoAllowed=" + ocoAllowed +
                ", isSpotTradingAllowed=" + isSpotTradingAllowed +
                ", isMarginTradingAllowed=" + isMarginTradingAllowed +
                '}';
    }
}
