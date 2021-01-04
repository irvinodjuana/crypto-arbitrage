package com.cryptoarb.UriBuilders;

import java.util.List;

public interface IBinanceUriBuilder {
    String buildBookTickerStreamUri(List<String> symbols);
}
