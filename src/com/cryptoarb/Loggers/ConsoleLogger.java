package com.cryptoarb.Loggers;

import java.time.LocalDateTime;

public class ConsoleLogger implements ILogger {
    public void log(String message) {
        LocalDateTime date = LocalDateTime.now();
        System.out.println(String.format("%s: %s", date, message));
    }
}
