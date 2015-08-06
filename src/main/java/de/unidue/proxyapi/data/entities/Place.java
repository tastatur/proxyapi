package de.unidue.proxyapi.data.entities;

import com.hp.hpl.jena.rdf.model.Resource;
import de.unidue.proxyapi.data.vocabulary.EnhancementResultVocabulary;

public class Place extends Entity {

    public Place(Resource internalRepresentation) {
        super(internalRepresentation);
    }

    public Double getLat() {
        return getSingleEntityPropValue(EnhancementResultVocabulary.GEO_LAT).getDouble();
    }

    public Double getLong() {
        return getSingleEntityPropValue(EnhancementResultVocabulary.GEO_LONG).getDouble();
    }
}
