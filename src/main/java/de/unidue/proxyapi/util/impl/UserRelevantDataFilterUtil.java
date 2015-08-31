package de.unidue.proxyapi.util.impl;

import de.unidue.proxyapi.data.entities.EntityProperty;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Da einige Daten, die für die API und Entwickler zwar nützlich sind (wie z.B. EntityRank),
 * für den Benutzer ehe keinen Sinn ergeben, soll ich die hier rausfiltern.
 */
public final class UserRelevantDataFilterUtil {

    /**
     * Filtere alle für den Benutzer irrelevante Daten raus.
     *
     * @param props             Ursprüngliuche Eigenschaften
     * @param propertyPredicate Prädikat, der entscheidet, was relevant uns was nicht ist.
     * @return Für den Benutzer relevante Eigenschaften
     */
    public static List<EntityProperty> filterProps(final List<EntityProperty> props, final Predicate<EntityProperty> propertyPredicate) {
        return props.parallelStream().filter(propertyPredicate).collect(Collectors.toList());
    }
}
