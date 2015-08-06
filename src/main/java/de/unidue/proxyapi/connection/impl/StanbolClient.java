package de.unidue.proxyapi.connection.impl;

import de.unidue.proxyapi.connection.EnhancementClient;
import de.unidue.proxyapi.data.entities.Entity;
import de.unidue.proxyapi.util.EnhancementEngine;
import de.unidue.proxyapi.util.EntitiesExtractorAnswerConverter;
import de.unidue.proxyapi.util.impl.StanbolAnswerConverter;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class StanbolClient implements EnhancementClient {

    private static final String STANBOL_ADDRESS_PROP = "de.unidue.stanbol.address";
    private static StanbolClient instance;

    private final String baseUrl;
    private final EntitiesExtractorAnswerConverter stanbolAnswerConverter = new StanbolAnswerConverter();
    private CloseableHttpClient httpClient;

    private StanbolClient(final String serverAddress) {
        this.baseUrl = serverAddress.concat("/enhancer/chain/");
    }

    public static synchronized StanbolClient getInstance() {
        if (instance == null) {
            instance = new StanbolClient(System.getProperty(STANBOL_ADDRESS_PROP));
            instance.init();
        }
        return instance;
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
    public Map<String, List<Entity>> getEntitiesForSnippets(final Map<String, String> snippets) {
        return getEntitiesForSnippets(snippets, EnhancementEngine.STANFORD);
    }

    @Override
    public Map<String, List<Entity>> getEntitiesForSnippets(final Map<String, String> snippets, final EnhancementEngine engine) {
        return snippets.entrySet().parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey, searchEntry -> getEntitiesForSnippet(searchEntry.getValue(), engine)));
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
