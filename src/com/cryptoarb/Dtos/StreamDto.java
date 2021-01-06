package com.cryptoarb.Dtos;

public class StreamDto {
    private String stream;
    private StreamBookTickerDto data;

    public StreamDto(String stream, StreamBookTickerDto data) {
        this.stream = stream;
        this.data = data;
    }

    public String getStream() {
        return stream;
    }

    public StreamBookTickerDto getData() {
        return data;
    }

    @Override
    public String toString() {
        return "StreamDto{" +
                "stream='" + stream + '\'' +
                ", data=" + data +
                '}';
    }
}
