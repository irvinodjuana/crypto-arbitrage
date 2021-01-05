package com.cryptoarb.HttpClients;

import com.cryptoarb.Dtos.BookTickerDto;
import com.cryptoarb.Dtos.ExchangeInfoDto;

import java.io.IOException;
import java.util.List;

public interface IBinanceHttpClient {
    List<BookTickerDto> getBookTickers() throws IOException, InterruptedException;
    ExchangeInfoDto getExchangeInfo() throws IOException, InterruptedException;
}
