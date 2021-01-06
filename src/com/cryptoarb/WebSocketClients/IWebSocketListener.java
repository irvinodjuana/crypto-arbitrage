package com.cryptoarb.WebSocketClients;

import com.cryptoarb.Dtos.StreamDto;

public interface IWebSocketListener {
    void onSocketUpdate(StreamDto streamDto);
}
