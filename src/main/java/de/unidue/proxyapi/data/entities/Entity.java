package de.unidue.proxyapi.data.entities;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.vocabulary.RDF;
import de.unidue.proxyapi.data.vocabulary.EnhancementResultVocabulary;
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
        return internalRepresentation.listProperties().mapWith(new ExtractPropertyFromStatement()).toList();
    }

    public List<String> getEntityTypes() {
        return internalRepresentation.listProperties(RDF.type).mapWith(entityTypeArc -> entityTypeArc.getLiteral().getString()).toList();
    }

    public String getUri() {
        return internalRepresentation.getURI();
    }

    public String getName() {
        return internalRepresentation.getLocalName();
    }

    public String getAbstract() {
        return getFirstLiteralValue(EnhancementResultVocabulary.ABSTRACT).getString();
    }

    public Double getConfidence() {
        return getFirstLiteralValue(EnhancementResultVocabulary.CONFIDENCE).getDouble();
    }

    public Double getEntityHubRank() {
        return getFirstLiteralValue(EnhancementResultVocabulary.ENTITYHUB_RANK).getDouble();
    }

    protected Literal getFirstLiteralValue(final Property property) {
        if (internalRepresentation.hasProperty(property)) {
            return internalRepresentation.getProperty(property).getLiteral();
        }
        return ResourceFactory.createPlainLiteral("");
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
