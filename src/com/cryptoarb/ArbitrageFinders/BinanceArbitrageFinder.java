package com.cryptoarb.ArbitrageFinders;

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
import java.util.stream.Collectors;

public class BinanceArbitrageFinder implements IBinanceArbitrageFinder {
    private Map<String, BookTicker> bookTickerMap;
    private double[][] weightMatrix;
    private List<Edge> edges;
    private BiMap<String, Integer> currencyIndexBiMap;
    private int numVertices;

    public BinanceArbitrageFinder() {
        bookTickerMap = new HashMap<>();
        edges = new ArrayList<>();
        currencyIndexBiMap = HashBiMap.create();
        numVertices = 0;
    }

    public void onSocketUpdate(StreamDto streamDto) {
        // TODO: update prices
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
            int baseIndex = currencyIndexBiMap.get(bookTicker.getBaseAsset());
            int quoteIndex = currencyIndexBiMap.get(bookTicker.getQuoteAsset());
            weightMatrix[quoteIndex][baseIndex] = -Math.log(1.0 / bookTicker.getAskPrice());
            weightMatrix[baseIndex][quoteIndex] = -Math.log(bookTicker.getBidPrice());
        }
        var graph = new DirectedGraph(numVertices, weightMatrix, edges);
        var arbitrageCycle = GraphUtils.findNegativeCycle(graph);

        // TODO: Extract method here and refactor to graph member variable
        System.out.println(arbitrageCycle.stream().map(idx -> currencyIndexBiMap.inverse().get(idx)).collect(Collectors.toList()));

        Thread.sleep(5000);
    }

    private int insertIntoBiMap(String currency) {
        if (!currencyIndexBiMap.containsKey(currency)) {
            currencyIndexBiMap.put(currency, numVertices);
            numVertices++;
        }
        return currencyIndexBiMap.get(currency);
    }

    private List<List<String>> findArbitrageCycles() {
        return new ArrayList<>();
    }
}
