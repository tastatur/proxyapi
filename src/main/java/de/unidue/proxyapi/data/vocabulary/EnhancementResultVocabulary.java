package de.unidue.proxyapi.data.vocabulary;

import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public final class EnhancementResultVocabulary {

    public static final String FISE_URI = "http://fise.iks-project.eu/ontology/";
    public static final String DBPEDIA_ONTOLOGY_URI = "http://dbpedia.org/ontology/";
    public static final String GEO_URI = "http://www.w3.org/2003/01/geo/wgs84_pos#";

    public static final Property ENTITY_REFERENCE = ResourceFactory.createProperty(FISE_URI, "entity-reference");
    public static final Property COUNTRY_TYPE = ResourceFactory.createProperty(DBPEDIA_ONTOLOGY_URI, "Country");
    public static final Property AREA_TOTAL = ResourceFactory.createProperty(DBPEDIA_ONTOLOGY_URI, "areaTotal");
    public static final Property ABSTRACT = ResourceFactory.createProperty(DBPEDIA_ONTOLOGY_URI, "abstract");
    public static final Property POPULATION_DENSITY = ResourceFactory.createProperty(DBPEDIA_ONTOLOGY_URI, "populationDensity");
    public static final Property GEO_LAT = ResourceFactory.createProperty(GEO_URI, "lat");
    public static final Property GEO_LONG = ResourceFactory.createProperty(GEO_URI, "long");
    public static final Property CONFIDENCE = ResourceFactory.createProperty(FISE_URI, "confidence");

    private EnhancementResultVocabulary() {
    }
}
