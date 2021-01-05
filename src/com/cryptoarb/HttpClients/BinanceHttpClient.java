package com.cryptoarb.HttpClients;

import com.cryptoarb.Configuration.IConfigurationProvider;
import com.cryptoarb.Consts.HttpConsts;
import com.cryptoarb.Dtos.BookTickerDto;
import com.cryptoarb.Dtos.ExchangeInfoDto;
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
    private String exchangeInfoUri;

    public BinanceHttpClient(IConfigurationProvider configurationProvider) {
        var binanceApiUri = configurationProvider.getBinanceApiUri();
        bookTickerUri = binanceApiUri + "/ticker/bookTicker";
        exchangeInfoUri = binanceApiUri + "/exchangeInfo";
    }

    public List<BookTickerDto> getBookTickers() throws IOException, InterruptedException {
        var response = httpGet(bookTickerUri);
        var type = new TypeToken<ArrayList<BookTickerDto>>() {}.getType();
        return deserializeJson(response, type);
    }

    public ExchangeInfoDto getExchangeInfo() throws IOException, InterruptedException {
        var response = httpGet(exchangeInfoUri);
        var type = new TypeToken<ExchangeInfoDto>() {}.getType();
        return deserializeJson(response, type);
    }

    private HttpResponse<String> httpGet(String uri) throws IOException, InterruptedException {
        var httpClient = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder()
                .GET()
                .header(HttpConsts.AcceptHeader, HttpConsts.ApplicationJson)
                .uri(URI.create(uri))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static <T> T deserializeJson(HttpResponse<String> response, Type type) {
        var gson = new Gson();
        return gson.fromJson(response.body(), type);
    }

}
