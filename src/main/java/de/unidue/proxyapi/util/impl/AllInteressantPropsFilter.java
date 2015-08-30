package de.unidue.proxyapi.util.impl;

import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.iterator.Filter;
import com.hp.hpl.jena.vocabulary.RDF;
import de.unidue.proxyapi.data.vocabulary.EnhancementResultVocabulary;

public class AllInteressantPropsFilter extends Filter<Statement> {

    @Override
    public boolean accept(Statement statement) {
        return !statement.getPredicate().equals(RDF.type) &&
                !statement.getPredicate().equals(EnhancementResultVocabulary.INDIVIDUALISED_GND);
    }
}
