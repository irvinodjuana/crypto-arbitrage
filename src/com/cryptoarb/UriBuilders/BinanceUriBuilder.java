package com.cryptoarb.UriBuilders;

import com.cryptoarb.Configuration.IConfigurationProvider;

import java.util.List;
import java.util.stream.Collectors;

public class BinanceUriBuilder implements IBinanceUriBuilder {
    private String multiStreamUriBase = "wss://stream.binance.com:9443/stream?streams=";

    public BinanceUriBuilder(IConfigurationProvider configurationProvider) {
        multiStreamUriBase = configurationProvider.getBinanceStreamUri() + "/streams?streams=";
    }

    public String buildBookTickerStreamUri(List<String> symbols) {
        String tickerNames = symbols.stream()
                .map(BinanceUriBuilder::getStreamName)
                .collect(Collectors.joining("/"));

        return multiStreamUriBase + tickerNames;
    }

    private static String getStreamName(String symbol) {
        return symbol.toLowerCase() + "@bookTicker";
    }
}
