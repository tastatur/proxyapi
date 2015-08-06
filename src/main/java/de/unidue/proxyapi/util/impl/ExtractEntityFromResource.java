package de.unidue.proxyapi.util.impl;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.Map1;
import com.hp.hpl.jena.vocabulary.RDF;
import de.unidue.proxyapi.data.entities.Entity;
import de.unidue.proxyapi.data.vocabulary.EnhancementResultVocabulary;
import de.unidue.proxyapi.util.MapRdfTypeToEntityUtil;

public class ExtractEntityFromResource implements Map1<Resource, Entity> {

    @Override
    public Entity map1(Resource enhancement) {
        final Resource dereferencedEntity = enhancement.getProperty(EnhancementResultVocabulary.ENTITY_REFERENCE).getResource();
        return MapRdfTypeToEntityUtil.getEntityFromType(dereferencedEntity);
    }
}
