package com.cryptoarb.WebSocketClients;

import javax.websocket.DeploymentException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IBinanceWebSocketClient {
    void connectPartial(List<String> symbols) throws URISyntaxException, IOException, DeploymentException;
    void connectAll() throws URISyntaxException, IOException, DeploymentException;
    void disconnect() throws IOException;
    void addListener(IWebSocketListener listener);
}
