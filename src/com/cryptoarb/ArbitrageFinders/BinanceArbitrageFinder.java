package com.cryptoarb.ArbitrageFinders;

import com.cryptoarb.Dtos.StreamBookTickerDto;
import com.cryptoarb.Dtos.StreamDto;
import com.cryptoarb.Models.BookTicker;
import com.cryptoarb.Models.DirectedGraph;
import com.cryptoarb.Models.Edge;
import com.cryptoarb.Utils.GraphUtils;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BinanceArbitrageFinder implements IBinanceArbitrageFinder {
    private Map<String, BookTicker> bookTickerMap;
    private BiMap<String, Integer> currencyIndexBiMap;
    private ConcurrentHashMap<String, StreamBookTickerDto> latestStreamData;

    private int numVertices;
    private double[][] weightMatrix;
    private List<Edge> edges;

    public BinanceArbitrageFinder() {
        bookTickerMap = new HashMap<>();
        currencyIndexBiMap = HashBiMap.create();
        latestStreamData = new ConcurrentHashMap<>();
        numVertices = 0;
        edges = new ArrayList<>();
    }

    public void onSocketUpdate(StreamDto streamDto) {
        var symbol = streamDto.getData().getSymbol();
        latestStreamData.put(symbol, streamDto.getData());
    }

    public void startSearch(List<BookTicker> bookTickers) throws InterruptedException {
        for (var bookTicker : bookTickers) {
            bookTickerMap.put(bookTicker.getSymbol(), bookTicker);
            int baseIndex = insertIntoBiMap(bookTicker.getBaseAsset());
            int quoteIndex = insertIntoBiMap(bookTicker.getQuoteAsset());
            edges.add(new Edge(baseIndex, quoteIndex));
            edges.add(new Edge(quoteIndex, baseIndex));
        }

        weightMatrix = new double[numVertices][numVertices];

        for (var bookTicker : bookTickers) {
            updateWeightMatrix(bookTicker);
        }

        while (true) {
            Thread.sleep(1000);
            System.out.println(findArbitrageCycles());
            updatePrices();
        }
    }

    private List<String> findArbitrageCycles() {
        var graph = new DirectedGraph(numVertices, weightMatrix, edges);
        var arbitrageCycle = GraphUtils.findNegativeCycle(graph);

        return arbitrageCycle.stream()
                .map(idx -> currencyIndexBiMap.inverse().get(idx))
                .collect(Collectors.toList());
    }

    private void updatePrices() {
        for (var streamBookTicker : latestStreamData.values()) {
            if (!bookTickerMap.containsKey(streamBookTicker.getSymbol())) {
                continue;
            }
            var bookTicker = bookTickerMap.get(streamBookTicker.getSymbol());
            bookTicker.setData(streamBookTicker);
            updateWeightMatrix(bookTicker);
        }

        latestStreamData.clear();
    }

    private void updateWeightMatrix(BookTicker bookTicker) {
        int baseIndex = currencyIndexBiMap.get(bookTicker.getBaseAsset());
        int quoteIndex = currencyIndexBiMap.get(bookTicker.getQuoteAsset());
        weightMatrix[quoteIndex][baseIndex] = -Math.log(1.0 / bookTicker.getAskPrice());
        weightMatrix[baseIndex][quoteIndex] = -Math.log(bookTicker.getBidPrice());
    }

    private int insertIntoBiMap(String currency) {
        if (!currencyIndexBiMap.containsKey(currency)) {
            currencyIndexBiMap.put(currency, numVertices);
            numVertices++;
        }
        return currencyIndexBiMap.get(currency);
    }
}
