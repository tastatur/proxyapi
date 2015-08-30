package de.unidue.proxyapi.data;

import de.unidue.proxyapi.data.entities.Entity;

import java.net.URL;
import java.util.List;

public class EnhancementResultEntry {
    private final String snippetText;
    private final URL snippetAddress;
    private final List<Entity> entities;

    public EnhancementResultEntry(final String snippetText, final URL snippetAddress, final List<Entity> entities) {
        this.snippetText = snippetText;
        this.snippetAddress = snippetAddress;
        this.entities = entities;
    }

    public String getSnippetText() {
        return snippetText;
    }

    public URL getSnippetAddress() {
        return snippetAddress;
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
