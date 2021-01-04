package com.cryptoarb.websocketclients;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@ClientEndpoint
public class BinanceWebSocketClient {
    private WebSocketContainer container;
    private Session userSession = null;

    public BinanceWebSocketClient() {
        container = ContainerProvider.getWebSocketContainer();
    }

    public void connect(String socketServer) {
        try {
            userSession = container.connectToServer(this, new URI(socketServer));
        } catch (DeploymentException | URISyntaxException | IOException e) {
            e.printStackTrace();
        }
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
        System.out.println(msg);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println(throwable);
    }

    public void disconnect() throws IOException {
        userSession.close();
    }
}
