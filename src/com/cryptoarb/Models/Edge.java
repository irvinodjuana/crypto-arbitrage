package com.cryptoarb.Models;

public class Edge {
    private final int src;
    private final int dest;

    public Edge(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }

    public int getSrc() {
        return src;
    }

    public int getDest() {
        return dest;
    }
}