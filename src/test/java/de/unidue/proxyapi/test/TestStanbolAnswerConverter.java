package de.unidue.proxyapi.test;

import de.unidue.proxyapi.data.entities.Country;
import de.unidue.proxyapi.data.entities.Entity;
import de.unidue.proxyapi.util.EntitiesExtractorAnswerConverter;
import de.unidue.proxyapi.util.impl.StanbolAnswerConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestStanbolAnswerConverter {

    private final EntitiesExtractorAnswerConverter stanbolAnswerConverter = new StanbolAnswerConverter();
    private String stanbolTestAnswer;

    @Before
    public void setUp() throws IOException {
        final URL testAnswerPath = Thread.currentThread().getContextClassLoader().getResource("stanbol-testanswer.rdf");
        if (testAnswerPath != null) {
            stanbolTestAnswer = new String(Files.readAllBytes(Paths.get(testAnswerPath.getPath())));
        }
    }

    @Test
    public void shouldConvertAllEntries() throws UnsupportedEncodingException {
        final List<Entity> entities = stanbolAnswerConverter.convertExtractorAnswer(new ByteArrayInputStream(stanbolTestAnswer.getBytes("UTF-8")));
        Assert.assertEquals(entities.size(), 5);
    }

    @Test
    public void shouldReturnValidDataForCountry() throws UnsupportedEncodingException  {
        final List<Entity> entities = stanbolAnswerConverter.convertExtractorAnswer(new ByteArrayInputStream(stanbolTestAnswer.getBytes("UTF-8")));
        Entity germany = null;
        for(Entity entity : entities) {
            if (entity.getUri().equals("http://de.dbpedia.org/resource/Deutschland")) {
                germany = entity;
                break;
            }
        }

        Assert.assertNotNull(germany);
        Assert.assertTrue(germany instanceof Country);
        Assert.assertNotNull(germany.getAbstract());
        Assert.assertEquals(((Country)germany).getPopulationDensity(), new Double(226));
    }

    @Test
    public void shouldHaveConfidence() throws UnsupportedEncodingException {
        final List<Entity> entities = stanbolAnswerConverter.convertExtractorAnswer(new ByteArrayInputStream(stanbolTestAnswer.getBytes("UTF-8")));
        entities.forEach(entity -> Assert.assertNotNull(entity.getConfidence()));
    }
}
