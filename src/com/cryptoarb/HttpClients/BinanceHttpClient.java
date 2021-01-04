package com.cryptoarb.HttpClients;

import com.cryptoarb.Configuration.IConfigurationProvider;
import com.cryptoarb.Consts.HttpConsts;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BinanceHttpClient implements IBinanceHttpClient {
    private String bookTickerUri;

    public BinanceHttpClient(IConfigurationProvider configurationProvider) {
        bookTickerUri = configurationProvider.getBinanceApiUri() + "/ticker/bookTicker";
    }

    public String getBookTickers() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header(HttpConsts.AcceptHeader, HttpConsts.ApplicationJson)
                .uri(URI.create(bookTickerUri))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }


}
