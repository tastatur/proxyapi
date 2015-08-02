package de.unidue.proxyapi.data.ontology;

public class Person extends Ontology {

    public String getBirthDate() {
        return this.getPropertyByName("birthDate").getValue();
    }

    public String getDeathDate() {
        return this.getPropertyByName("deathDate").getValue();
    }
}
