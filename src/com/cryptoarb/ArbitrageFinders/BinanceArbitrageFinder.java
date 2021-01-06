package com.cryptoarb.ArbitrageFinders;

import com.cryptoarb.Dtos.StreamDto;
import com.cryptoarb.Models.BookTicker;
import com.cryptoarb.Models.Edge;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinanceArbitrageFinder implements IBinanceArbitrageFinder {
    private Map<String, BookTicker> bookTickerMap;
    private List<List<Double>> priceMatrix;
    private List<Edge> edges;
    private BiMap<Integer, String> currencyIndexBiMap;

    public BinanceArbitrageFinder() {
        bookTickerMap = new HashMap<>();
        priceMatrix = new ArrayList<>();
        edges = new ArrayList<>();
        currencyIndexBiMap = HashBiMap.create();
    }

    public void startSearch(List<BookTicker> bookTickerList) throws InterruptedException {
        Thread.sleep(5000);
    }

    public void onSocketUpdate(StreamDto streamDto) {
        System.out.println(streamDto.getData());
    }
}
