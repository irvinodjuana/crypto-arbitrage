package com.cryptoarb.UriBuilders;

import com.cryptoarb.Configuration.IConfigurationProvider;

import java.util.List;
import java.util.stream.Collectors;

public class BinanceUriBuilder implements IBinanceUriBuilder {
    private String multiStreamUriBase;

    public BinanceUriBuilder(IConfigurationProvider configurationProvider) {
        multiStreamUriBase = configurationProvider.getBinanceStreamUri() + "/stream?streams=";
    }

    public String buildPartialTickersStreamUri(List<String> symbols) {
        var tickerNames = symbols.stream()
                .map(BinanceUriBuilder::getStreamName)
                .collect(Collectors.joining("/"));

        return multiStreamUriBase + tickerNames;
    }

    public String buildAllTickersStreamUri() {
        return multiStreamUriBase + "!bookTicker";
    }

    private static String getStreamName(String symbol) {
        return symbol.toLowerCase() + "@bookTicker";
    }
}
