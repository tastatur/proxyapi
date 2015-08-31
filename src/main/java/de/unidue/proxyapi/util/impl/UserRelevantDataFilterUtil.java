package de.unidue.proxyapi.util.impl;

import com.hp.hpl.jena.util.iterator.Filter;
import de.unidue.proxyapi.data.EnhancementResultEntry;
import de.unidue.proxyapi.data.EnhancementResults;
import de.unidue.proxyapi.data.entities.Entity;
import de.unidue.proxyapi.data.entities.EntityProperty;

import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Da einige Daten, die für die API und Entwickler zwar nützlich sind (wie z.B. EntityRank),
 * für den Benutzer ehe keinen Sinn ergeben, soll ich die hier rausfiltern.
 */
public final class UserRelevantDataFilterUtil {

    /**
     * Filtere alle für den Benutzer irrelevante Daten und die Entitäten ohne Parameter raus.
     *
     * @param oldEnhancementResults Ursprüngliuche Anreicherungen
     * @param propertyPredicate     Prädikat, der entscheidet, was relevant uns was nicht ist.
     * @return Für den Benutzer relevante Eigenschaften
     */
    public static EnhancementResults filterProps(final EnhancementResults oldEnhancementResults, final Filter<EntityProperty> propertyPredicate) {
        final EnhancementResults newEnhancementResults = new EnhancementResults();
        oldEnhancementResults.getEnhancementResults().parallelStream()
                .forEach(enhancementResultEntry -> enhancementResultEntry.getEntities()
                        .forEach(entity -> entity.setKeepPropsFilter(propertyPredicate)));

        oldEnhancementResults.getEnhancementResults().forEach(enhancementResultEntry -> {
            final String snippet = enhancementResultEntry.getSnippetText();
            final URL website = enhancementResultEntry.getSnippetAddress();
            final List<Entity> entities = enhancementResultEntry.getEntities().parallelStream().filter(entity -> !entity.getAllProperties().isEmpty())
                    .collect(Collectors.toList());
            if (!entities.isEmpty()) {
                newEnhancementResults.addEnhancementResult(new EnhancementResultEntry(snippet, website, entities));
            }
        });

        return newEnhancementResults;
    }
}
