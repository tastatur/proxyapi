package de.unidue.proxyapi.connection.impl;

import de.unidue.proxyapi.connection.EnhancementClient;
import de.unidue.proxyapi.data.entities.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractEnhancementClient implements EnhancementClient {

    @Override
    public Map<String, List<Entity>> filterEmptyResults(final Map<String, List<Entity>> enhancementResults) {
        final Map<String, List<Entity>> nonEmptyEnhancements = new HashMap<>();
        enhancementResults.forEach((url, enhancements) -> {
            if (!enhancements.isEmpty()) {
                nonEmptyEnhancements.put(url, enhancements);
            }
        });
        return nonEmptyEnhancements;
    }
}
