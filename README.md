# crypto-arbitrage
Cryptocurrency Arbitrage Finder

## Overview
This project is for finding triangular arbitrage opportunities on the Binance cryptocurrency exchange. The graph algorithms used can find arbitrage cycles of arbitrary length. WebSocket streams with the Binance API are used for live price updates, and profits are calculated net of fees.

## Setup

### Requirements
- Java 11

### Run Locally
```sh
git clone https://github.com/irvinodjuana/crypto-arbitrage
cd crypto-arbitrage/
java -jar out/artifacts/crypto_arbitrage_jar/crypto-arbitrage.jar
```
