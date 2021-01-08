package com.cryptoarb.WebSocketClients;

import com.cryptoarb.Dtos.ExchangeInfoDto;
import com.cryptoarb.Dtos.StreamDto;
import com.cryptoarb.Loggers.ILogger;
import com.cryptoarb.UriBuilders.IBinanceUriBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.websocket.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@ClientEndpoint
public class BinanceWebSocketClient implements IBinanceWebSocketClient {
    private IBinanceUriBuilder binanceUriBuilder;
    private ILogger logger;
    private WebSocketContainer container;
    private Session userSession = null;
    private List<IWebSocketListener> listeners;

    public BinanceWebSocketClient(IBinanceUriBuilder binanceUriBuilder, ILogger logger) {
        this.binanceUriBuilder = binanceUriBuilder;
        this.logger = logger;
        container = ContainerProvider.getWebSocketContainer();
        listeners = new ArrayList<>();
    }

    public void connectPartial(List<String> symbols) throws URISyntaxException, IOException, DeploymentException {
        String socketServer = binanceUriBuilder.buildPartialTickersStreamUri(symbols);
        disconnect();
        userSession = container.connectToServer(this, new URI(socketServer));
    }

    @Override
    public void connectAll() throws URISyntaxException, IOException, DeploymentException {
        String socketServer = binanceUriBuilder.buildAllTickersStreamUri();
        disconnect();
        userSession = container.connectToServer(this, new URI(socketServer));
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        logger.log("Socket connected");
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.log("Socket disconnected");
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        var streamType = new TypeToken<StreamDto>() {}.getType();
        StreamDto streamDto = deserializeJson(msg, streamType);
        listeners.forEach(l -> l.onSocketUpdate(streamDto));
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println(throwable);
    }

    public void disconnect() throws IOException {
        if (userSession != null) {
            userSession.close();
        }
    }

    @Override
    public void addListener(IWebSocketListener listener) {
        listeners.add(listener);
    }

    private static <T> T deserializeJson(String json, Type type) {
        var gson = new Gson();
        return gson.fromJson(json, type);
    }
}
