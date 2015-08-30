package de.unidue.proxyapi.connection;

import de.unidue.proxyapi.data.EnhancementResults;
import de.unidue.proxyapi.data.SearchSnippets;
import de.unidue.proxyapi.util.EnhancementEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface EnhancementClient {

    default Logger getLogger() {
        return LoggerFactory.getLogger(EnhancementClient.class);
    }

    /**
     * Suche nach Ontologien in den Suchsnippets
     *
     * @param snippets Die Suchsnippets, die untersucht werden sollen und die Liste von URLs, die von der Suchmaschine gefunden wurden.
     * Wichtig! Diese API ist keine Schnitstelle zum Bing oder Google, die führt keine Webseitensuche durch, sondern extrahiert die Entitäten
     * aus den vorher gefundenen Informationen! Bitte benutze die API von Karatassis, falls du eine Bing-API brauchst.
     * @param engine Die Enginekette, die verwendet werden soll
     * @return Gefundene Ontologien.
     */
    EnhancementResults getEntitiesForSnippets(final SearchSnippets snippets, final EnhancementEngine engine);
}
