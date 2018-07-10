package com.chenZ.dave.util.tools;
import java.util.concurrent.ThreadLocalRandom;

public class TraceIDGenerator {

    public TraceIDGenerator() {
    }

    public static String generate() {
        long currentTime = System.currentTimeMillis();
        long timeStamp = currentTime % 1000000L;
        int randomNumber = ThreadLocalRandom.current().nextInt(1000000);
        long traceID = timeStamp * 10000L + (long)randomNumber;
        return Long.toHexString(traceID).toUpperCase();
    }
}
