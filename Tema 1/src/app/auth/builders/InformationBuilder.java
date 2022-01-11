package app.auth.builders;

import app.auth.Credentials;

import java.util.Collection;

public class InformationBuilder {
    public Credentials credentials;

    public Collection<String> favoriteGames;

    public String name;

    public String country;

    public InformationBuilder(Credentials credentials) {
        this.credentials = credentials;
    }

    public InformationBuilder setFavoriteGames(Collection<String> favoriteGames) {
        this.favoriteGames = favoriteGames;

        return this;
    }

    public InformationBuilder setName(String name) {
        this.name = name;

        return this;
    }

    public InformationBuilder setCountry(String country) {
        this.country = country;

        return this;
    }

}