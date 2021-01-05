package com.cryptoarb.Dtos;

import java.util.Arrays;

public class ExchangeInfoDto {
    private String timezone;
    private long serverTime;
    private RateLimitDto[] rateLimits;
    private SymbolDto[] symbols;

    public ExchangeInfoDto(String timezone, long serverTime, RateLimitDto[] rateLimits, SymbolDto[] symbols) {
        this.timezone = timezone;
        this.serverTime = serverTime;
        this.rateLimits = rateLimits;
        this.symbols = symbols;
    }

    public String getTimezone() {
        return timezone;
    }

    public long getServerTime() {
        return serverTime;
    }

    public RateLimitDto[] getRateLimits() {
        return rateLimits;
    }

    public SymbolDto[] getSymbols() {
        return symbols;
    }

    @Override
    public String toString() {
        return "ExchangeInfoDto{" +
                "timezone='" + timezone + '\'' +
                ", serverTime=" + serverTime +
                ", rateLimits=" + Arrays.toString(rateLimits) +
                ", symbols=" + Arrays.toString(symbols) +
                '}';
    }
}
