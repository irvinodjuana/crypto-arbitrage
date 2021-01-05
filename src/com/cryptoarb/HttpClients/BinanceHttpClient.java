package com.cryptoarb.HttpClients;

import com.cryptoarb.Configuration.IConfigurationProvider;
import com.cryptoarb.Consts.HttpConsts;
import com.cryptoarb.Dtos.BookTickerDto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class BinanceHttpClient implements IBinanceHttpClient {
    private String bookTickerUri;

    public BinanceHttpClient(IConfigurationProvider configurationProvider) {
        bookTickerUri = configurationProvider.getBinanceApiUri() + "/ticker/bookTicker";
    }

    public List<BookTickerDto> getBookTickers() throws IOException, InterruptedException {
        HttpResponse<String> response = httpGet(bookTickerUri);
        Type type = new TypeToken<ArrayList<BookTickerDto>>() {}.getType();
        return deserializeJson(response, type);
    }

    private HttpResponse<String> httpGet(String uri) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header(HttpConsts.AcceptHeader, HttpConsts.ApplicationJson)
                .uri(URI.create(uri))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private List<BookTickerDto> deserializeJson(HttpResponse<String> response, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(response.body(), type);
    }

}
