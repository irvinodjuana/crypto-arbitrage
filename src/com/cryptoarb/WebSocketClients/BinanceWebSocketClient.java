package com.cryptoarb.WebSocketClients;

import com.cryptoarb.UriBuilders.IBinanceUriBuilder;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@ClientEndpoint
public class BinanceWebSocketClient implements IBinanceWebSocketClient {
    private WebSocketContainer container;
    private Session userSession = null;
    private IBinanceUriBuilder binanceUriBuilder;
    private List<IWebSocketListener> listeners;

    public BinanceWebSocketClient(IBinanceUriBuilder binanceUriBuilder) {
        container = ContainerProvider.getWebSocketContainer();
        this.binanceUriBuilder = binanceUriBuilder;
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
        System.out.println("Socket connected");
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("Socket disconnected");
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        listeners.forEach(IWebSocketListener::onSocketUpdate);
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
}
