package de.unidue.proxyapi.data.entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.HashMap;

/**
 * Die Basisklasse, die die Entitäten, die zu keiner anderer Klasse passen, darstellt
 * Properties ist ein Map<URI, Property>, wo URI die interne URI des Properties ist (soll nur intern verwendet werden)
 */
public class Entity extends HashMap<String, Property> {

    public static final String DBPEDIA_BASE_URL = "http://dbpedia.org/ontology/";

    public Collection<Property> getAllProperties() {
        return this.values();
    }

    public Property getPropertyByUri(String uri) {
        return this.get(uri);
    }

    public Property getPropertyByName(String name) {
        return this.getPropertyByUri(DBPEDIA_BASE_URL.concat(name));
    }

    public String getEntityType() {
        throw new NotImplementedException();
    }
}
