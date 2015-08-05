package de.unidue.proxyapi.data.entities;

public class Person extends Entity {

    public String getBirthDate() {
        return this.getPropertyByName("birthDate").getValue();
    }

    public String getDeathDate() {
        return this.getPropertyByName("deathDate").getValue();
    }
}
