package de.unidue.proxyapi.examples;

import de.unidue.proxyapi.connection.EnhancementClient;
import de.unidue.proxyapi.connection.impl.StanbolClient;

public class StanbolClientUsageExample {

    public static void main(String[] argv) {
        System.setProperty("de.unidue.stanbol.address", "http://localhost:8080/");
        EnhancementClient stanbolClient = StanbolClient.getInstance();
    }
}
