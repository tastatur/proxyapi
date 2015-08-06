package de.unidue.proxyapi.util.impl;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import de.unidue.proxyapi.data.entities.Entity;
import de.unidue.proxyapi.data.vocabulary.EnhancementResultVocabulary;
import de.unidue.proxyapi.util.EntitiesExtractorAnswerConverter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Konvertiere die Antwort von Stanbol zu dem API-Format
 */
public class StanbolAnswerConverter implements EntitiesExtractorAnswerConverter {

    @Override
    public List<Entity> convertExtractorAnswer(final InputStream content) {
        final Model rawResponseModel = ModelFactory.createDefaultModel();
        final Set<Entity> foundEntities = new HashSet<>();

        rawResponseModel.read(content, null);
        rawResponseModel.listResourcesWithProperty(EnhancementResultVocabulary.ENTITY_REFERENCE).
                mapWith(new ExtractEntityFromResource()).forEachRemaining(foundEntities::add);
        return new ArrayList<>(foundEntities);
    }
}
