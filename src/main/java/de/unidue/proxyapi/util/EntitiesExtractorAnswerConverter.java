package de.unidue.proxyapi.util;

import de.unidue.proxyapi.data.entities.Entity;

import java.io.InputStream;
import java.util.List;

/**
 * Konvertiere die Antwort von dem Entity Extraktionengine in den API-Format
 */
public interface EntitiesExtractorAnswerConverter {

    List<Entity> convertExtractorAnswer(final InputStream content);
}
