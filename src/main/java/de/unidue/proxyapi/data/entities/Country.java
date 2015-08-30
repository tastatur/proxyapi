package de.unidue.proxyapi.data.entities;

import com.hp.hpl.jena.rdf.model.Resource;
import de.unidue.proxyapi.data.vocabulary.EnhancementResultVocabulary;

public class Country extends Place {

    public Country(final Resource internalRepresentation) {
        super(internalRepresentation);
    }

    public Double getAreaTotal() {
        return getFirstLiteralValue(EnhancementResultVocabulary.AREA_TOTAL).getDouble();
    }

    public Double getPopulationDensity() {
        return getFirstLiteralValue(EnhancementResultVocabulary.POPULATION_DENSITY).getDouble();
    }
}
