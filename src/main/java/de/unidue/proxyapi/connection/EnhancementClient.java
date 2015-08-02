package de.unidue.proxyapi.connection;

import de.unidue.proxyapi.data.ontology.Ontology;
import de.unidue.proxyapi.util.EnhancementEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public interface EnhancementClient {

    default Logger getLogger() {
        return LoggerFactory.getLogger(EnhancementClient.class);
    }

    /**
     * Suche nach Ontologien in den Suchsnippets und verwende dabei den Default-Engine als Enhancementkette
     *
     * @param snippets Suchsnippets, wo nach Entitäten gesuchgt werden soll (Eine Map von URL der Webseite auf den Text des Snippets)
     * @return Gefundene Ontologien (Map von URL der Webseite zu den auf der seite gefundenen Ontologien)
     */
    Map<String, List<Ontology>> getOntologysForSnippets(final Map<String, String> snippets);

    /**
     * Suche nach Ontologien in den Suchsnippets
     *
     * @param snippets Suchsnippets, wo nach Entitäten gesuchgt werden soll (Eine Map von URL der Webseite auf den Text des Snippets)
     * @param engine   Die Enginekette, die verwendet werden soll
     * @return Gefundene Ontologien (Map von URL der Webseite zu den auf der seite gefundenen Ontologien)
     */
    Map<String, List<Ontology>> getOntologysForSnippets(final Map<String, String> snippets, final EnhancementEngine engine);
}
