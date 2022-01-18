package app.shared;

import app.auth.Account;
import app.auth.Credentials;
import app.auth.builders.InformationBuilder;
import app.core.Entity;
import app.entities.factories.CharacterFactory;
import app.types.CellEnum;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;

public class JSONReader {
    public List<Account> readAccounts() throws Exception {
        List<Account> accountList = new ArrayList<Account>();

        Object rawAccounts = new JSONParser().parse(new FileReader("accounts.json"));
        JSONObject typedAccounts = (JSONObject) rawAccounts;

        JSONArray accountsArray = (JSONArray) typedAccounts.get("accounts");
        Iterator accountItr = accountsArray.iterator();
        while (accountItr.hasNext()) {
            Map currentAccountMap = (Map) accountItr.next();

            String accountName = (String) currentAccountMap.get("name");
            String country = (String) currentAccountMap.get("country");
            Integer completedMaps = Integer.valueOf((String) currentAccountMap.get("maps_completed"));

            JSONObject rawCredentials = (JSONObject) currentAccountMap.get("credentials");
            String credentialsEmail = (String) rawCredentials.get("email");
            String credentialsPassword = (String) rawCredentials.get("password");
            Credentials credentials = new Credentials(credentialsEmail, credentialsPassword);

            JSONArray rawFavoriteGames = (JSONArray) currentAccountMap.get("favorite_games");
            Iterator favoriteGamesItr = rawFavoriteGames.iterator();
            ArrayList<String> favoriteGames = new ArrayList<String>();
            while (favoriteGamesItr.hasNext()) {
                favoriteGames.add((String) favoriteGamesItr.next());
            }

            InformationBuilder infoBuilder = new InformationBuilder(credentials)
                    .setFavoriteGames(favoriteGames)
                    .setName(accountName)
                    .setCountry(country);


            List<Entity> characters = new ArrayList<Entity>();
            CharacterFactory characterFactory = new CharacterFactory();
            JSONArray rawCharacters = (JSONArray) currentAccountMap.get("characters");
            Iterator charactersItr = rawCharacters.iterator();
            while (charactersItr.hasNext()) {
                Map character = (Map) charactersItr.next();

                String name = (String) character.get("name");
                String profession = (String) character.get("profession");
                Integer currentLevel = Integer.valueOf((String) character.get("level"));
                Integer currentXp = ((Long) character.get("experience")).intValue();

                characters.add(characterFactory.createCharacter(profession, name, currentLevel, currentXp));
            }

            accountList.add(new Account(infoBuilder, characters, completedMaps));
        }

        return accountList;
    }

    public Map<CellEnum, List<String>> readStories() throws Exception {
        Map<CellEnum, List<String>> stories = new HashMap<CellEnum, List<String>>();
        for (CellEnum cellType: CellEnum.values()) {
            stories.put(cellType, new ArrayList<String>());
        }

        Object rawStories = new JSONParser().parse(new FileReader("stories.json"));
        JSONObject typedStories =(JSONObject) rawStories;

        JSONArray rawStoriesArr = (JSONArray) typedStories.get("stories");
        Iterator storiesItr = rawStoriesArr.iterator();
        while (storiesItr.hasNext()) {
            JSONObject currentStory = (JSONObject) storiesItr.next();
            CellEnum currentStoryType = CellEnum.valueOf((String) currentStory.get("type"));
            String currentStoryQuote = (String) currentStory.get("value");
            stories.get(currentStoryType).add(currentStoryQuote);
        }

        return stories;
    }
}
