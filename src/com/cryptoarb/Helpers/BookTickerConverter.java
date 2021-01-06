package com.cryptoarb.Helpers;

import com.cryptoarb.Dtos.BookTickerDto;
import com.cryptoarb.Dtos.ExchangeInfoDto;
import com.cryptoarb.Dtos.SymbolDto;
import com.cryptoarb.Models.BookTicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookTickerConverter {
    public static List<BookTicker> createBookTickers(List<BookTickerDto> bookTickerDtos, ExchangeInfoDto exchangeInfoDto) {
        var symbolDtos = exchangeInfoDto.getSymbols();
        var symbolMap = new HashMap<String, SymbolDto>();

        for (var symbolDto : symbolDtos) {
            symbolMap.put(symbolDto.getSymbol(), symbolDto);
        }

        var localBookTickers = new ArrayList<BookTicker>();

        for (var bookTickerDto : bookTickerDtos) {
            var bookTicker = new BookTicker(
                    bookTickerDto.getSymbol(),
                    bookTickerDto.getBidPrice(),
                    bookTickerDto.getBidQty(),
                    bookTickerDto.getAskPrice(),
                    bookTickerDto.getAskQty());

            var symbolDto = symbolMap.get(bookTicker.getSymbol());
            bookTicker.setBaseAsset(symbolDto.getBaseAsset());
            bookTicker.setQuoteAsset(symbolDto.getQuoteAsset());

            if (bookTicker.isActive()) {
                localBookTickers.add(bookTicker);
            }
        }

        return localBookTickers;
    }
}
