package de.unidue.proxyapi.data;

import java.util.ArrayList;
import java.util.List;

public class EnhancementResults {
    private List<EnhancementResultEntry> enhancementResults = new ArrayList<>();

    public void addEnhancementResult(final EnhancementResultEntry enhancementResult) {
        enhancementResults.add(enhancementResult);
    }

    public List<EnhancementResultEntry> getEnhancementResults() {
        return enhancementResults;
    }
}
