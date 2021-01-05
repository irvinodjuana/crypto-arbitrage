package com.cryptoarb.UriBuilders;

import java.util.List;

public interface IBinanceUriBuilder {
    String buildPartialTickersStreamUri(List<String> symbols);
    String buildAllTickersStreamUri();
}
