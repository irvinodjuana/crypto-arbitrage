package com.cryptoarb.ProcessManagers;

import com.cryptoarb.ArbitrageFinders.IBinanceArbitrageFinder;
import com.cryptoarb.Helpers.BookTickerConverter;
import com.cryptoarb.HttpClients.IBinanceHttpClient;
import com.cryptoarb.WebSocketClients.IBinanceWebSocketClient;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;

public class BinanceProcessManager implements IBinanceProcessManager {
    private IBinanceHttpClient binanceHttpClient;
    private IBinanceWebSocketClient binanceWebSocketClient;
    private IBinanceArbitrageFinder binanceArbitrageFinder;

    public BinanceProcessManager(
            IBinanceHttpClient binanceHttpClient,
            IBinanceWebSocketClient binanceWebSocketClient,
            IBinanceArbitrageFinder binanceArbitrageFinder) {
        this.binanceHttpClient = binanceHttpClient;
        this.binanceWebSocketClient = binanceWebSocketClient;
        this.binanceArbitrageFinder = binanceArbitrageFinder;
    }

    public void start() {
        try {
            connectAllStreams();
        } catch (IOException | InterruptedException | URISyntaxException | DeploymentException e) {
            e.printStackTrace();
        }
    }

    private void connectAllStreams() throws IOException, InterruptedException, URISyntaxException, DeploymentException {
        var bookTickerDtos = binanceHttpClient.getBookTickers();
        var exchangeInfo = binanceHttpClient.getExchangeInfo();

        var bookTickers = BookTickerConverter.createBookTickers(bookTickerDtos, exchangeInfo);

        binanceWebSocketClient.addListener(binanceArbitrageFinder);
        binanceWebSocketClient.connectAll();
        binanceArbitrageFinder.startSearch(bookTickers);

        binanceWebSocketClient.disconnect();
    }
}
