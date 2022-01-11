package app.auth;

import app.auth.builders.InformationBuilder;
import app.core.Entity;

import java.util.Collection;
import java.util.List;

public class Account {
    private Information info;

    private List<Entity> characters;

    private Integer gamesPlayed;

    public Account(InformationBuilder informationBuilder, List<Entity> characters, Integer gamesPlayed) {
        this.info = new Information(informationBuilder);
        this.characters = characters;
        this.gamesPlayed = gamesPlayed;
    }

    public String getEmail() {
        return info.getCredentials().getEmail();
    }

    public Boolean login(String email, String password) {
        return info.getCredentials().login(email, password);
    }

    public List<Entity> getCharacters() {
        return characters;
    }

    class Information {
        private Credentials credentials;

        private Collection<String> favoriteGames;

        private String name;

        private String country;

        public Information(InformationBuilder builder) {
            credentials = builder.credentials;
            favoriteGames = builder.favoriteGames;
            name = builder.name;
            country = builder.country;
        }

        public Credentials getCredentials() {
            return credentials;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append("Credentials: ").append(credentials).append('\n');
            str.append("Favorite Games: ").append(favoriteGames).append('\n');
            str.append("Name: ").append(name).append('\n');
            str.append("Country: ").append(country).append('\n');

            return str.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Information:\n").append(info);
        str.append("Characters:\n");
        for (Entity character: characters) {
            str.append(character);
        }
        str.append("Games Played: ").append(gamesPlayed);

        return str.toString();
    }
}
