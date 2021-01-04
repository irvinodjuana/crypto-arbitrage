package com.cryptoarb.UriBuilders;

import java.util.List;
import java.util.stream.Collectors;

public class BinanceUriBuilder implements IBinanceUriBuilder {
    private static final String BookTickerStreamUriBase = "wss://stream.binance.com:9443/stream?streams=";

    public BinanceUriBuilder() {
    }

    public String buildBookTickerStreamUri(List<String> symbols) {
        String tickerNames = symbols.stream()
                .map(BinanceUriBuilder::getStreamName)
                .collect(Collectors.joining("/"));

        return BookTickerStreamUriBase + tickerNames;
    }

    private static String getStreamName(String symbol) {
        return symbol.toLowerCase() + "@bookTicker";
    }
}
