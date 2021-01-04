package com.cryptoarb.HttpClients;

import java.io.IOException;

public interface IBinanceHttpClient {
    public String getBookTickers() throws IOException, InterruptedException;
}
