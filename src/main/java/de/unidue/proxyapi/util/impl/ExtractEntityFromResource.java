package de.unidue.proxyapi.util.impl;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.Map1;
import de.unidue.proxyapi.data.entities.Entity;
import de.unidue.proxyapi.data.vocabulary.EnhancementResultVocabulary;
import de.unidue.proxyapi.util.MapRdfTypeToEntityUtil;

public class ExtractEntityFromResource implements Map1<Resource, Entity> {

    @Override
    public Entity map1(Resource enhancement) {
        final Resource dereferencedEntity = enhancement.getProperty(EnhancementResultVocabulary.ENTITY_REFERENCE).getResource();
        dereferencedEntity.addProperty(EnhancementResultVocabulary.CONFIDENCE, enhancement.getProperty(EnhancementResultVocabulary.CONFIDENCE).getLiteral());
        return MapRdfTypeToEntityUtil.getEntityFromType(dereferencedEntity);
    }
}
