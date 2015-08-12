package de.unidue.proxyapi.util;

public enum EnhancementEngine {
    STANFORD("stanford-nerchain"), TIGER("tiger-nerchain"), PIG("pig-nerchain");

    private final String engineName;

    EnhancementEngine(final String engineName) {
        this.engineName = engineName;
    }

    @Override
    public String toString() {
        return this.engineName;
    }
}
