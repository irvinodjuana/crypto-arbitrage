package com.cryptoarb.ArbitrageFinders;

public class BinanceArbitrageFinder implements IBinanceArbitrageFinder {
    public void onSocketUpdate() {
        System.out.println("Message acknowledged");
    }
}
