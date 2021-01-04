package com.cryptoarb;

import com.cryptoarb.uribuilders.BinanceUriBuilder;
import com.cryptoarb.websocketclients.BinanceWebSocketClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        List<String> symbols = new ArrayList<>();
        symbols.add("ETHBTC");
        symbols.add("LTCBTC");
        symbols.add("QTUMETH");
        String streamUri = BinanceUriBuilder.buildBookTickerStreamUri(symbols);

        BinanceWebSocketClient webSocketClient = new BinanceWebSocketClient();
        webSocketClient.connect(streamUri);

        Thread.sleep(5000);

        webSocketClient.disconnect();

        System.out.println("Program end.");
    }
}
