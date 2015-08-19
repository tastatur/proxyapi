package de.unidue.proxyapi.util;

public enum EnhancementEngine {
    STANFORD_BOTH("stanford-nerchain-both"), STANFORD_DEWAC("stanford-nerchain-dewac"), STANFORD_HGC("stanford-nerchain-hgc"),
    MITIE_PIG("mitie-nerchain-pig"), MITIE_TIGER("mitie-nerchain-tiger"),
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
