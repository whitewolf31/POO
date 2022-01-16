package app;

import app.auth.Account;
import app.core.Cell;
import app.core.Entity;
import app.core.Grid;
import app.entities.Character;
import app.entities.Enemy;
import app.entities.Shop;
import app.errors.InvalidCommandException;
import app.player.Inventory;
import app.player.Spell;
import app.player.inventory.Potion;
import app.shared.JSONReader;
import app.shared.Logger;
import app.types.CellEnum;
import app.types.DirectionEnum;
import app.types.GameTypeEnum;
import app.views.core.Wrapper;
import app.views.init.LoginComponent;
import sun.misc.Signal;
import sun.misc.SignalHandler;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static Game instance = null;

    public List<Account> accounts;

    public Map<CellEnum, List<String>> cellList;

    public GameTypeEnum gameType = null;

    private Scanner stdinReader;

    public Grid gameGrid;

    private Boolean gameFinished;

    public Wrapper guiWrapper;

    public Account selectedAccount;

    public Character selectedCharacter;

    private Logger logger;

    private Game() {}

    public static Game getInstance() {
        if (instance == null) instance = new Game();

        return instance;
    }

    public void run() {
        stdinReader = new Scanner(System.in);
        JSONReader jsonReader = new JSONReader();
        logger = Logger.getInstance();

        // CLI/GUI selection
        do {
            System.out.print("CLI or GUI? (C/g)");
            String gameType = stdinReader.nextLine();
            if (gameType.isEmpty() || gameType.equals("C") || gameType.equals("c")) this.gameType = GameTypeEnum.CLI;
            else if (gameType.equals("G") || gameType.equals("g")) this.gameType = GameTypeEnum.GUI;
        } while (this.gameType == null);

        if (this.gameType == GameTypeEnum.GUI) {
            runGUI();
            return;
        }

        // Account Login
        selectedAccount = null;
        do {
            System.out.println("-----LOGIN-----");
            System.out.print("Email: ");
            String email = stdinReader.nextLine();
            System.out.print("Password: ");
            String password = stdinReader.nextLine();
            try {
                accounts = jsonReader.readAccounts();
                for (Account account : accounts) {
                    if (account.getEmail().equals(email)) {
                        if (account.login(email, password)) selectedAccount = account;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (selectedAccount == null) System.out.println("Wrong email/password");

        } while (selectedAccount == null);

        // Character Selection
        System.out.println("Available Characters:");
        for (Entity character: selectedAccount.getCharacters()) {
            System.out.println(character);
        }
        selectedCharacter = null;
        do {
            System.out.println("Select Character (Input name):");
            String characterName = stdinReader.nextLine();
            for (Entity entity: selectedAccount.getCharacters()) {
                Character castedCharacter = (Character) entity;
                if (castedCharacter.getName().equals(characterName)) {
                    selectedCharacter = castedCharacter;
                }
            }
            if (selectedCharacter == null) {
                System.out.println("Invalid Selection.");
            }
        } while (selectedCharacter == null);

        try {
            cellList = jsonReader.readStories();
        } catch (Exception e) {
            e.printStackTrace();
        }
        gameGrid = Grid.generateGrid(5, 5, selectedCharacter, false);
        gameFinished = false;
        while (!gameFinished) {
            System.out.println(gameGrid);
            showStory(gameGrid.getCurrentCell().getType());
            showOptionsAndDecide();
            if (gameFinished) break;
            if (gameGrid.getCurrentCell().getType() == CellEnum.FINISH) gameFinished = true;
            else {
                Boolean correctInstruction;
                do {
                    System.out.print("Where next? (E, N, W, S): ");
                    String nextInstruction = stdinReader.nextLine();
                    if (nextInstruction.equals("E")) {
                        correctInstruction = gameGrid.navigate(DirectionEnum.EAST);
                    } else if (nextInstruction.equals("N")) {
                        correctInstruction = gameGrid.navigate(DirectionEnum.NORTH);
                    } else if (nextInstruction.equals("S")) {
                        correctInstruction = gameGrid.navigate(DirectionEnum.SOUTH);
                    } else if (nextInstruction.equals("W")) {
                        correctInstruction = gameGrid.navigate(DirectionEnum.WEST);
                    } else correctInstruction = false;

                    if (!correctInstruction) System.out.println("Wrong Direction.");
                } while (!correctInstruction);
            }
        }
    }

    private String getCurrentCharacterInfo() {
        Inventory playerInventory = gameGrid.getPlayer().getInventory();
        StringBuilder sb = new StringBuilder();
        sb.append("Available Coins: ")
                .append(playerInventory.getCoins())
                .append(". Available Weight: ")
                .append(playerInventory.getRemainingWeight())
                .append(". Would you like to buy anything? (Input number/N): ");

        return sb.toString();
    }

    public void showOptionsAndDecide() {
        Cell currentCell = gameGrid.getCurrentCell();
        Character player = gameGrid.getPlayer();

        if (currentCell.getType() == CellEnum.EMPTY && !currentCell.getVisited()) {
            Random rand = new Random();
            Integer randomNumber = rand.nextInt(5);
            if (randomNumber == 0) {
                Integer numOfCoins = rand.nextInt(3) + 1;
                player.getInventory().giveCoins(numOfCoins);
                System.out.println("You have found " + numOfCoins + " coins!");
            }
        }

        if (currentCell.getType() == CellEnum.SHOP) {
            System.out.println(currentCell.getCellElement());
            System.out.print(getCurrentCharacterInfo());
            Boolean invalidCommand;
            Boolean buySomethingElse;
            do {
                buySomethingElse = false;
                invalidCommand = false;
                String command = stdinReader.nextLine();
                try {
                    if (command.equals("N")) break;
                    else {
                        Integer parsedCommand = Integer.valueOf(command);
                        Shop shop = (Shop) currentCell.getCellElement();
                        if (parsedCommand > shop.getNumberOfPotions()) throw new InvalidCommandException("Invalid Command. Please select a valid index (Input number/N): ");
                        Potion fetchedPotion = shop.getPotionByIndex(parsedCommand - 1);
                        try {
                            player.buyPotion(fetchedPotion);
                            shop.buyPotion(parsedCommand - 1);
                            System.out.println("You have bought a " + fetchedPotion.getClass().getSimpleName());
                            System.out.print("Do you want to buy something else? (N/y): ");
                            String response = stdinReader.nextLine();
                            if (response.equals("y") || response.equals("Y")) {
                                buySomethingElse = true;
                                System.out.println(currentCell.getCellElement());
                                System.out.print(getCurrentCharacterInfo());
                            }
                        } catch (Exception e) {
                            System.out.print(e);
                            invalidCommand = true;
                        }
                    }
                } catch (Exception e) {
                    if (e instanceof NumberFormatException) System.out.print("Invalid Command. Please select a valid index (Input number/N): ");
                    else System.out.print(e);
                    invalidCommand = true;
                }
            } while (invalidCommand || buySomethingElse);
        } else if (currentCell.getType() == CellEnum.ENEMY) {
            Enemy enemy = (Enemy) currentCell.getCellElement();
            if (enemy.getCurrentHP() > 0)
                do {
                    Boolean invalidCommand;
                    Boolean usedPotion;
                    do {
                        invalidCommand = false;
                        usedPotion = false;
                        try {
                            System.out.println(player.showPlayerInfo());
                            System.out.println("What do you want to do?");
                            System.out.print("Use Ability / Use Potion / Normal Attack (A/P/N): ");
                            String command = stdinReader.nextLine();
                            if (command.equals("a") || command.equals("A")) {
                                Boolean innerInvalidCommand;
                                do {
                                    innerInvalidCommand = false;
                                    try {
                                        System.out.println(player.listSpells());
                                        System.out.println("Which spell do you want to use? (Input Number/N): ");
                                        command = stdinReader.nextLine();
                                        if (command.equals("N") || command.equals("n")) {
                                            invalidCommand = true;
                                            break;
                                        } else {
                                            Integer parsedCommand = Integer.valueOf(command);
                                            if (parsedCommand > player.getSpellsSize()) throw new InvalidCommandException("Invalid Command.");
                                            Spell spellToUse = player.getSpellByIndex(parsedCommand - 1);
                                            if (spellToUse.getManaRequired() <= player.getCurrentMana()) {
                                                player.useSpell(spellToUse, enemy);
                                            } else {
                                                throw new InvalidCommandException("Not enough mana for this potion.");
                                            }
                                        }
                                    } catch (Exception e) {
                                        if (e instanceof NumberFormatException) System.out.print("Invalid Command. Please select a valid index (Input number/N): ");
                                        else System.out.println(e);
                                        innerInvalidCommand = true;
                                    }
                                } while (innerInvalidCommand);
                            } else if (command.equals("p") || command.equals("P")) {
                                Boolean innerInvalidCommand;
                                do {
                                    innerInvalidCommand = false;
                                    try {
                                        if (player.getInventory().getPotionsSize() == 0) {
                                            System.out.println("You have no potions!");
                                            break;
                                        }
                                        System.out.println(player.getInventory().listPotions());
                                        System.out.println("Which potion do you want to use? (Input Number/N): ");
                                        command = stdinReader.nextLine();
                                        if (command.equals("N") || command.equals("n")) {
                                            invalidCommand = true;
                                            break;
                                        } else {
                                            Integer parsedCommand = Integer.valueOf(command);
                                            if (parsedCommand > player.getInventory().getPotionsSize()) throw new InvalidCommandException("Invalid Command.");
                                            Potion potionToUse = player.getInventory().selectAndRemovePotion(parsedCommand - 1);
                                            potionToUse.usePotion(player);
                                            usedPotion = true;
                                        }
                                    } catch (Exception e) {
                                        if (e instanceof NumberFormatException) System.out.print("Invalid Command. Please select a valid index (Input number/N): ");
                                        else System.out.println(e);
                                        innerInvalidCommand = true;
                                    }
                                } while (innerInvalidCommand);
                            } else if (command.equals("n") || command.equals("N")) {
                                enemy.receiveDamage(player.getDamage());
                            } else throw new InvalidCommandException("Invalid Command");
                        } catch (Exception e) {
                            System.out.println(e);
                            invalidCommand = true;
                        }
                    } while (invalidCommand || usedPotion);

                    if (enemy.getCurrentHP() <= 0) {
                        Random rand = new Random();
                        int randNum = rand.nextInt(3) + 5;
                        System.out.println("Congratulations! You have killed an enemy and gained " + randNum + " coins and 10XP!");
                        player.getInventory().giveCoins(randNum);
                        player.addToCurrentXp(10);
                        System.out.println(gameGrid);
                        break;
                    }

                    Random rand = new Random();
                    int chanceToUseAbility = rand.nextInt(4);
                    boolean usedAbility = false;
                    if (chanceToUseAbility == 0) {
                        if (enemy.getSpellListSize() > 0) {
                            int randomSpellIndex = rand.nextInt(enemy.getSpellListSize());
                            Spell spellToUse = enemy.getSpellByIndex(randomSpellIndex);
                            System.out.println("Enemy used " + spellToUse.getClass().getSimpleName() + " against you.");
                            enemy.useSpell(spellToUse, player);
                            usedAbility = true;
                        }
                    }
                    if (!usedAbility) {
                        System.out.println("Enemy used basic attack against you!");
                        player.receiveDamage(enemy.getDamage());
                    }

                    if (player.getCurrentHP() <= 0) {
                        System.out.println("You died. Game over :(");
                        gameFinished = true;
                        break;
                    }
                } while (true);
            }

        currentCell.visit();
    }

    public void showStory(CellEnum cellType) {
        Random rand = new Random();
        List<String> storyList = cellList.get(cellType);
        System.out.println(storyList.get(rand.nextInt(storyList.size())));
    }

    public void runGUI() {
        guiWrapper = Wrapper.getInstance();
        setCurrentView(LoginComponent.getInstance(), "Login");
    }

    public void setCurrentView(JComponent component, String title) {
        guiWrapper.changeComponent(component, title);
    }

    public void runHardCoded() {
        System.out.print("Warning! You are running the game in Hardcodede mode.");
        System.out.println("This means that whatever command you input, the default hardcode command will take place instead");
        System.out.println("In order to change this behaviour, go to app.test.Test and change game.runHardCoded() to game.run()");
        gameType = GameTypeEnum.CLI;
        stdinReader = new Scanner(System.in);
        JSONReader jsonReader = new JSONReader();
        System.out.println("-----LOGIN-----");
        System.out.print("Email: ");
        stdinReader.nextLine();
        String email = "marcel@yahoo.com";
        System.out.print("Password: ");
        stdinReader.nextLine();
        String password = "asdewq123";
        try {
            accounts = jsonReader.readAccounts();
            for (Account account : accounts) {
                if (account.getEmail().equals(email)) {
                    if (account.login(email, password)) selectedAccount = account;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Available Characters:");
        for (Entity character: selectedAccount.getCharacters()) {
            System.out.println(character);
        }
        selectedCharacter = null;
        System.out.println("Select Character (Input name):");
        stdinReader.nextLine();
        String characterName = "Kameron Neppl";
        for (Entity entity: selectedAccount.getCharacters()) {
            Character castedCharacter = (Character) entity;
            if (castedCharacter.getName().equals(characterName)) {
                selectedCharacter = castedCharacter;
            }
        }
        try {
            cellList = jsonReader.readStories();
        } catch (Exception e) {
            e.printStackTrace();
        }
        gameGrid = Grid.generateGrid(5, 5, selectedCharacter, true);
        selectedCharacter.getInventory().giveCoins(12);
        // (0, 0)
        System.out.println(gameGrid);
        showStory(gameGrid.getCurrentCell().getType());
        System.out.print("Where next? (E, N, W, S): ");
        stdinReader.nextLine();
        gameGrid.getCurrentCell().visit();
        gameGrid.navigate(DirectionEnum.EAST);
        // (0, 1)
        System.out.println(gameGrid);
        showStory(gameGrid.getCurrentCell().getType());
        System.out.print("Where next? (E, N, W, S): ");
        stdinReader.nextLine();
        gameGrid.getCurrentCell().visit();
        gameGrid.navigate(DirectionEnum.EAST);
        // (0, 2)
        System.out.println(gameGrid);
        showStory(gameGrid.getCurrentCell().getType());
        System.out.print("Where next? (E, N, W, S): ");
        stdinReader.nextLine();
        gameGrid.getCurrentCell().visit();
        gameGrid.navigate(DirectionEnum.EAST);
        // (0, 3) Shop
        System.out.println(gameGrid);
        showStory(gameGrid.getCurrentCell().getType());
        Shop currentShop = (Shop) gameGrid.getCurrentCell().getCellElement();
        System.out.println(currentShop);
        System.out.print(getCurrentCharacterInfo());
        stdinReader.nextLine();
        int healthPotionIndex = -1, manaPotionIndex = -1;
        List<Potion> potionList = currentShop.getPotionList();
        for (Potion potion: potionList) {
            if (potion.getClass().getSimpleName().equals("HealthPotion") && healthPotionIndex == -1) healthPotionIndex = potionList.indexOf(potion);
            if (potion.getClass().getSimpleName().equals("ManaPotion") && manaPotionIndex == -1) manaPotionIndex = potionList.indexOf(potion);
        }
        Potion fetchedHealthPotion = currentShop.getPotionByIndex(healthPotionIndex);
        Potion fetchedManaPotion = currentShop.getPotionByIndex(manaPotionIndex);
        try {
            selectedCharacter.buyPotion(fetchedHealthPotion);
            selectedCharacter.buyPotion(fetchedManaPotion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        currentShop.buyPotion(potionList.indexOf(fetchedHealthPotion));
        currentShop.buyPotion(potionList.indexOf(fetchedManaPotion));
        System.out.println("You have bought a " + fetchedHealthPotion.getClass().getSimpleName());
        System.out.print("Do you want to buy something else? (N/y): ");
        stdinReader.nextLine();
        System.out.println("You have bought a " + fetchedManaPotion.getClass().getSimpleName());
        System.out.print("Do you want to buy something else? (N/y): ");
        stdinReader.nextLine();
        System.out.print("Where next? (E, N, W, S): ");
        stdinReader.nextLine();
        gameGrid.getCurrentCell().visit();
        gameGrid.navigate(DirectionEnum.EAST);
        // (0, 4)
        System.out.println(gameGrid);
        showStory(gameGrid.getCurrentCell().getType());
        System.out.print("Where next? (E, N, W, S): ");
        stdinReader.nextLine();
        gameGrid.getCurrentCell().visit();
        gameGrid.navigate(DirectionEnum.SOUTH);
        // (1, 4)
        System.out.println(gameGrid);
        showStory(gameGrid.getCurrentCell().getType());
        System.out.print("Where next? (E, N, W, S): ");
        stdinReader.nextLine();
        gameGrid.getCurrentCell().visit();
        gameGrid.navigate(DirectionEnum.SOUTH);
        // (2, 4)
        System.out.println(gameGrid);
        showStory(gameGrid.getCurrentCell().getType());
        System.out.print("Where next? (E, N, W, S): ");
        stdinReader.nextLine();
        gameGrid.getCurrentCell().visit();
        gameGrid.navigate(DirectionEnum.SOUTH);
        // (3, 4)
        System.out.println(gameGrid);
        showStory(gameGrid.getCurrentCell().getType());
        Enemy enemy = (Enemy) gameGrid.getCurrentCell().getCellElement();
        System.out.println(selectedCharacter.showPlayerInfo());
        System.out.println("What do you want to do?");
        System.out.print("Use Ability / Use Potion / Normal Attack (A/P/N): ");
        stdinReader.nextLine();
        Potion selectedPotion = selectedCharacter.getInventory().selectAndRemovePotion(0);
        selectedPotion.usePotion(selectedCharacter);
        System.out.println("You have used " + selectedPotion.getClass().getSimpleName());
        System.out.println(selectedCharacter.showPlayerInfo());
        System.out.println("What do you want to do?");
        System.out.print("Use Ability / Use Potion / Normal Attack (A/P/N): ");
        stdinReader.nextLine();
        selectedPotion = selectedCharacter.getInventory().selectAndRemovePotion(0);
        selectedPotion.usePotion(selectedCharacter);
        System.out.println("You have used " + selectedPotion.getClass().getSimpleName());
        while (enemy.getCurrentHP() > 0) {
            System.out.println(selectedCharacter.showPlayerInfo());
            System.out.println("What do you want to do?");
            System.out.print("Use Ability / Use Potion / Normal Attack (A/P/N): ");
            stdinReader.nextLine();
            Spell spellToUse = null;
            for (Spell spell: selectedCharacter.getSpellList()) {
                if (spell.getManaRequired() < selectedCharacter.getCurrentMana()) {
                    spellToUse = spell;
                }
            }
            if (spellToUse != null) {
                selectedCharacter.useSpell(spellToUse, enemy);
            } else {
                enemy.receiveDamage(selectedCharacter.getDamage());
            }

            if (enemy.getCurrentHP() <= 0) {
                Random rand = new Random();
                int randNum = rand.nextInt(3) + 5;
                System.out.println("Congratulations! You have killed an enemy and gained" + randNum + " coins and 10XP!");
                selectedCharacter.getInventory().giveCoins(randNum);
                selectedCharacter.addToCurrentXp(10);
                break;
            }
            Random rand = new Random();
            int chanceToUseAbility = rand.nextInt(4);
            boolean usedAbility = false;
            if (chanceToUseAbility == 0) {
                if (enemy.getSpellListSize() > 0) {
                    int randomSpellIndex = rand.nextInt(enemy.getSpellListSize());
                    spellToUse = enemy.getSpellByIndex(randomSpellIndex);
                    System.out.println("Enemy used " + spellToUse.getClass().getSimpleName() + " against you.");
                    enemy.useSpell(spellToUse, selectedCharacter);
                    usedAbility = true;
                }
            }
            if (!usedAbility) {
                System.out.println("Enemy used basic attack against you!");
                selectedCharacter.receiveDamage(enemy.getDamage());
            }

            if (selectedCharacter.getCurrentHP() <= 0) {
                System.out.println("You would have died. HP reset to 50 for demonstration purposes");
                selectedCharacter.setCurrentHP(50); // Prevent from dying
            }
        }
        System.out.println(gameGrid);showStory(gameGrid.getCurrentCell().getType());
        System.out.print("Where next? (E, N, W, S): ");
        stdinReader.nextLine();
        gameGrid.getCurrentCell().visit();
        gameGrid.navigate(DirectionEnum.SOUTH);
        System.out.println("You won.");
    }
}
