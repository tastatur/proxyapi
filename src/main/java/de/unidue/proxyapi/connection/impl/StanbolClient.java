package de.unidue.proxyapi.connection.impl;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import de.unidue.proxyapi.connection.EnhancementClient;
import de.unidue.proxyapi.data.ontology.Ontology;
import de.unidue.proxyapi.util.EnhancementEngine;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StanbolClient implements EnhancementClient {

    private static final String STANBOL_ADDRESS_PROP = "de.unidue.stanbol.address";
    private static StanbolClient instance;

    private final String baseUrl;
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
        defaultRequestHeaders.add(new BasicHeader("Accept", "text/turtle"));
        defaultRequestHeaders.add(new BasicHeader("Content-type", "text/plain"));
        return defaultRequestHeaders;
    }

    @Override
    public Map<String, List<Ontology>> getOntologysForSnippets(final Map<String, String> snippets) {
        return getOntologysForSnippets(snippets, EnhancementEngine.STANFORD);
    }

    @Override
    public Map<String, List<Ontology>> getOntologysForSnippets(final Map<String, String> snippets, final EnhancementEngine engine) {
        return snippets.entrySet().parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey, searchEntry -> getEntitiesForSnippet(searchEntry.getValue(),  engine)));
    }

    private List<Ontology> getEntitiesForSnippet(final String snippet, final EnhancementEngine engine) {
        List<Ontology> foundEntities = null;
        final HttpContext httpContext = HttpClientContext.create();
        final HttpPut enhancementRequest = new HttpPut(this.baseUrl.concat(engine.toString()));
        try (CloseableHttpResponse stanbolResponse = this.httpClient.execute(enhancementRequest, httpContext)) {
            HttpEntity responseBody = stanbolResponse.getEntity();
            foundEntities = convertRawStanbolAnswer(responseBody.getContent());
        } catch (IOException e) {
            e.printStackTrace();
            getLogger().error("Kann die SUchergebnisse nicht anreichern!", e);
        }
        return foundEntities;
    }

    private List<Ontology> convertRawStanbolAnswer(final InputStream content) {
        final Model rawResponseModel = ModelFactory.createDefaultModel();
        rawResponseModel.read(content, null);
        return null;
    }
}
