package com.cryptoarb.HttpClients;

import com.cryptoarb.Dtos.BookTickerDto;

import java.io.IOException;
import java.util.List;

public interface IBinanceHttpClient {
    public List<BookTickerDto> getBookTickers() throws IOException, InterruptedException;
}
