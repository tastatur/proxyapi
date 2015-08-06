package de.unidue.proxyapi.data.entities;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
import de.unidue.proxyapi.data.vocabulary.EnhancementResultVocabulary;
import de.unidue.proxyapi.util.impl.AllInteressantPropsFilter;
import de.unidue.proxyapi.util.impl.ExtractPropertyFromStatement;

import java.util.List;

/**
 * Die Basisklasse, die die Entitäten, die zu keiner anderer Klasse passen, darstellt
 */
public class Entity {

    private final Resource internalRepresentation;

    public Entity(final Resource internalRepresentation) {
        this.internalRepresentation = internalRepresentation;
    }

    public List<EntityProperty> getAllProperties() {
        return internalRepresentation.listProperties().filterKeep(new AllInteressantPropsFilter()).mapWith(new ExtractPropertyFromStatement()).toList();
    }

    public List<String> getEntityTypes() {
        return internalRepresentation.listProperties(RDF.type).mapWith(entityTypeArc -> entityTypeArc.getLiteral().getString()).toList();
    }

    public Literal getSingleEntityPropValue(final Property property) {
        return internalRepresentation.getProperty(property).getLiteral();
    }

    public String getSingleEntityPropValueAsString(final Property property) {
        return getSingleEntityPropValue(property).getString();
    }

    public String getUri() {
        return internalRepresentation.getURI();
    }

    public String getAbstract() {
        return getSingleEntityPropValueAsString(EnhancementResultVocabulary.ABSTRACT);
    }

    public Double getConfidence() {
        return getSingleEntityPropValue(EnhancementResultVocabulary.CONFIDENCE).getDouble();
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Entity && internalRepresentation.getURI().equals(((Entity) object).internalRepresentation.getURI());
    }

    @Override
    public int hashCode() {
        return internalRepresentation.getURI().hashCode();
    }
}
