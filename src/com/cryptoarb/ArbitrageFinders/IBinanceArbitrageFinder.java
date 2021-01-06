package com.cryptoarb.ArbitrageFinders;

import com.cryptoarb.Models.BookTicker;
import com.cryptoarb.WebSocketClients.IWebSocketListener;

import java.util.List;

public interface IBinanceArbitrageFinder extends IWebSocketListener {
    void startSearch(List<BookTicker> bookTickerList) throws InterruptedException;
}
