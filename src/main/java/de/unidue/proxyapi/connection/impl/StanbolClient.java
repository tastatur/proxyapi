package de.unidue.proxyapi.connection.impl;

import de.unidue.proxyapi.connection.EnhancementClient;
import de.unidue.proxyapi.data.EnhancementResultEntry;
import de.unidue.proxyapi.data.EnhancementResults;
import de.unidue.proxyapi.data.SearchSnippets;
import de.unidue.proxyapi.data.entities.Entity;
import de.unidue.proxyapi.util.EnhancementEngine;
import de.unidue.proxyapi.util.EntitiesExtractorAnswerConverter;
import de.unidue.proxyapi.util.impl.StanbolAnswerConverter;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public final class StanbolClient implements EnhancementClient {

    private final String baseUrl;
    private final EntitiesExtractorAnswerConverter stanbolAnswerConverter = new StanbolAnswerConverter();
    private CloseableHttpClient httpClient;

    public StanbolClient(final String serverAddress) {
        this.baseUrl = serverAddress.concat("/enhancer/chain/");
        init();
    }

    private void init() {
        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        final List<Header> defaultRequestHeaders = getDefaultStanbolRequestHeaders();
        this.httpClient = HttpClients.custom().setConnectionManager(connectionManager).setDefaultHeaders(defaultRequestHeaders).build();
    }

    private List<Header> getDefaultStanbolRequestHeaders() {
        final List<Header> defaultRequestHeaders = new ArrayList<>();
        defaultRequestHeaders.add(new BasicHeader("Accept", "application/rdf+xml"));
        defaultRequestHeaders.add(new BasicHeader("Content-type", "text/plain"));
        return defaultRequestHeaders;
    }

    @Override
    public EnhancementResults getEntitiesForSnippets(final SearchSnippets snippets, final EnhancementEngine engine) {
        final EnhancementResults enhancementResults = new EnhancementResults();
        snippets.getSearchSnippets().parallelStream().map(searchSnippet -> {
            final URL websiteUrl = searchSnippet.getWebsiteUrl();
            final String snippetText = searchSnippet.getSnippetText();

            final List<Entity> entities = getEntitiesForSnippet(snippetText, engine);
            return new EnhancementResultEntry(snippetText, websiteUrl, entities);
        }).filter(enhancementResultEntry -> !enhancementResultEntry.getEntities().isEmpty()).forEach(enhancementResults::addEnhancementResult);
        return enhancementResults;
    }

    private List<Entity> getEntitiesForSnippet(final String snippet, final EnhancementEngine engine) {
        List<Entity> foundEntities = null;
        final HttpContext httpContext = HttpClientContext.create();
        final HttpPost enhancementRequest = generatePostRequest(snippet, engine);
        try (CloseableHttpResponse stanbolResponse = this.httpClient.execute(enhancementRequest, httpContext)) {
            HttpEntity responseBody = stanbolResponse.getEntity();
            foundEntities = stanbolAnswerConverter.convertExtractorAnswer(responseBody.getContent());
        } catch (IOException e) {
            e.printStackTrace();
            getLogger().error("Kann die Suchergebnisse nicht anreichern!", e);
        }
        return foundEntities;
    }

    private HttpPost generatePostRequest(final String snippet, final EnhancementEngine engine) {
        final HttpPost enhancementRequest = new HttpPost(this.baseUrl.concat(engine.toString()));
        try {
            enhancementRequest.setEntity(new StringEntity(snippet));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            getLogger().error("Kann die Suchergebnisse nicht anreichern!", e);
        }
        return enhancementRequest;
    }
}
