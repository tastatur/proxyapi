package de.unidue.proxyapi.util;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import de.unidue.proxyapi.data.entities.Country;
import de.unidue.proxyapi.data.entities.Entity;
import de.unidue.proxyapi.data.vocabulary.EnhancementResultVocabulary;

public final class MapRdfTypeToEntityUtil {

    private MapRdfTypeToEntityUtil() {
    }

    public static Entity getEntityFromType(final Resource dereferencedEntity) {
        if (dereferencedEntity.hasProperty(RDF.type, EnhancementResultVocabulary.COUNTRY_TYPE)) {
            return new Country(dereferencedEntity);
        }
        return new Entity(dereferencedEntity);
    }
}
