package de.unidue.proxyapi.util.impl;

import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.Filter;
import de.unidue.proxyapi.data.vocabulary.EnhancementResultVocabulary;

public class AllInteressantPropsFilter extends Filter<Statement> {

    @Override
    public boolean accept(Statement statement) {
        return statement.getPredicate().getURI().startsWith(EnhancementResultVocabulary.DBPEDIA_ONTOLOGY_URI) ||
                statement.getPredicate().getURI().startsWith(EnhancementResultVocabulary.GEO_URI);
    }
}
