package com.cryptoarb;

import com.cryptoarb.Configuration.ConfigurationProvider;
import com.cryptoarb.Configuration.IConfigurationProvider;
import com.cryptoarb.HttpClients.BinanceHttpClient;
import com.cryptoarb.HttpClients.IBinanceHttpClient;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        IConfigurationProvider configurationProvider = new ConfigurationProvider();
        IBinanceHttpClient binanceHttpClient = new BinanceHttpClient(configurationProvider);

        System.out.println(binanceHttpClient.getBookTickers());
    }
}
