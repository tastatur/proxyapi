package de.unidue.proxyapi.data;

import java.util.ArrayList;
import java.util.List;

public class SearchSnippets {
    private final List<SearchSnippet> searchSnippets = new ArrayList<>();

    public void addSearchSnippet(final SearchSnippet searchSnippet) {
        this.searchSnippets.add(searchSnippet);
    }

    public List<SearchSnippet> getSearchSnippets() {
        return searchSnippets;
    }
}
