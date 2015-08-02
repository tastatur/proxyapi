package de.unidue.proxyapi.data.ontology;

/**
 * Diese Klasse repräsentiert eine Eigenschaft der Ontologie (z.B. Todestadum oder PLZ)
 */
public class Property {

    private String name;
    private String uri;
    private String lang;
    private String value;
    private String typeUri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTypeUri() {
        return typeUri;
    }

    public void setTypeUri(String typeUri) {
        this.typeUri = typeUri;
    }
}
