package com.cryptoarb.ProcessManagers;

import com.cryptoarb.Dtos.BookTickerDto;
import com.cryptoarb.HttpClients.IBinanceHttpClient;
import com.cryptoarb.WebSocketClients.IBinanceWebSocketClient;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class BinanceProcessManager implements IBinanceProcessManager {
    private IBinanceHttpClient binanceHttpClient;
    private IBinanceWebSocketClient binanceWebSocketClient;

    public BinanceProcessManager(IBinanceHttpClient binanceHttpClient, IBinanceWebSocketClient binanceWebSocketClient) {
        this.binanceHttpClient = binanceHttpClient;
        this.binanceWebSocketClient = binanceWebSocketClient;
    }

    public void start() {
        try {
            connectAllStreams();
        } catch (IOException | InterruptedException | URISyntaxException | DeploymentException e) {
            e.printStackTrace();
        }
    }

    private void connectAllStreams() throws IOException, InterruptedException, URISyntaxException, DeploymentException {
        var bookTickers = binanceHttpClient.getBookTickers();
        var symbols = bookTickers.stream()
                .map(BookTickerDto::getSymbol)
                .collect(Collectors.toList());

        binanceWebSocketClient.connectAll();
        Thread.sleep(500);
        binanceWebSocketClient.disconnect();
    }
}
