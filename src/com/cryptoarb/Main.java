package com.cryptoarb;

import com.cryptoarb.websocketclients.BinanceWebSocketClient;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Hello world");

        BinanceWebSocketClient webSocketClient = new BinanceWebSocketClient();
        webSocketClient.connect("wss://stream.binance.com:9443/ws/ethbtc@bookTicker");
        Thread.sleep(5000);
        webSocketClient.disconnect();
    }
}
