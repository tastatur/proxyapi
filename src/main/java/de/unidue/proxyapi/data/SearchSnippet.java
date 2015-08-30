package de.unidue.proxyapi.data;

import java.net.URL;

public class SearchSnippet {
    private final URL websiteUrl;
    private final String snippetText;

    public SearchSnippet(final URL websiteUrl, final String snippetText) {
        this.websiteUrl = websiteUrl;
        this.snippetText = snippetText;
    }

    public URL getWebsiteUrl() {
        return websiteUrl;
    }

    public String getSnippetText() {
        return snippetText;
    }
}
