package com.cryptoarb.Dtos;

public class RateLimitDto {
    public String rateLimitType;
    public String interval;
    public int intervalNum;
    public int limit;

    public RateLimitDto(String rateLimitType, String interval, int intervalNum, int limit) {
        this.rateLimitType = rateLimitType;
        this.interval = interval;
        this.intervalNum = intervalNum;
        this.limit = limit;
    }

    public String getRateLimitType() {
        return rateLimitType;
    }

    public String getInterval() {
        return interval;
    }

    public int getIntervalNum() {
        return intervalNum;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "RateLimitDto{" +
                "rateLimitType='" + rateLimitType + '\'' +
                ", interval='" + interval + '\'' +
                ", intervalNum=" + intervalNum +
                ", limit=" + limit +
                '}';
    }
}
