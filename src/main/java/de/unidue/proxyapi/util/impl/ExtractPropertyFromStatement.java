package de.unidue.proxyapi.util.impl;

import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.Map1;
import de.unidue.proxyapi.data.entities.EntityProperty;

public class ExtractPropertyFromStatement implements Map1<Statement, EntityProperty> {

    @Override
    public EntityProperty map1(Statement statement) {
        final EntityProperty entityProperty = new EntityProperty();
        entityProperty.setUri(statement.getPredicate().getURI());

        if (statement.getObject().isLiteral()) {
            entityProperty.setValue(statement.getLiteral().getString());
            entityProperty.setLang(statement.getLiteral().getLanguage());
            entityProperty.setTypeUri(statement.getLiteral().getDatatypeURI());
            entityProperty.setName(statement.getPredicate().getLocalName());
        } else {
            entityProperty.setValue(statement.getResource().getURI());
            entityProperty.setExternalResourceLink(true);
        }
        return entityProperty;
    }
}
