package de.unidue.proxyapi.util;

public enum EnhancementEngine {
    STANFORD_BOTH("stanford-nerchain-both"), STANFORD_DEWAC("stanford-nerchain-dewac"), STANFORD_HGC("stanford-nerchain-hgc"),
    TIGER("tiger-nerchain"), PIG("pig-nerchain");

    private final String engineName;

    EnhancementEngine(final String engineName) {
        this.engineName = engineName;
    }

    @Override
    public String toString() {
        return this.engineName;
    }
}
