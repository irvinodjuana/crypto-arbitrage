package com.cryptoarb;

import com.cryptoarb.Configuration.ConfigurationProvider;
import com.cryptoarb.Configuration.IConfigurationProvider;
import com.cryptoarb.HttpClients.BinanceHttpClient;
import com.cryptoarb.HttpClients.IBinanceHttpClient;
import com.cryptoarb.ProcessManagers.BinanceProcessManager;
import com.cryptoarb.ProcessManagers.IBinanceProcessManager;
import com.cryptoarb.UriBuilders.BinanceUriBuilder;
import com.cryptoarb.UriBuilders.IBinanceUriBuilder;
import com.cryptoarb.WebSocketClients.BinanceWebSocketClient;
import com.cryptoarb.WebSocketClients.IBinanceWebSocketClient;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        IConfigurationProvider configurationProvider = new ConfigurationProvider();
        IBinanceHttpClient binanceHttpClient = new BinanceHttpClient(configurationProvider);
        IBinanceUriBuilder binanceUriBuilder = new BinanceUriBuilder(configurationProvider);
        IBinanceWebSocketClient binanceWebSocketClient = new BinanceWebSocketClient(binanceUriBuilder);
        IBinanceProcessManager binanceProcessManager = new BinanceProcessManager(binanceHttpClient, binanceWebSocketClient);

        binanceProcessManager.start();
    }
}
