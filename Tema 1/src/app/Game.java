package app;

import app.auth.Account;
import app.core.Cell;
import app.core.Entity;
import app.core.Grid;
import app.dtos.LoginInfoDto;
import app.entities.Character;
import app.entities.Enemy;
import app.entities.Shop;
import app.errors.InvalidCommandException;
import app.player.Inventory;
import app.player.Spell;
import app.player.inventory.Potion;
import app.shared.JSONReader;
import app.types.CellEnum;
import app.types.DirectionEnum;
import app.types.GameTypeEnum;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static Game instance = null;

    private List<Account> accounts;

    private Map<CellEnum, List<String>> cellList;

    private GameTypeEnum gameType = null;

    private Scanner stdinReader;

    private Grid gameGrid;

    private Boolean gameFinished;

    private Game() {}

    public static Game getInstance() {
        if (instance == null) instance = new Game();

        return instance;
    }

    public void run() {
        stdinReader = new Scanner(System.in);
        JSONReader jsonReader = new JSONReader();

        // CLI/GUI selection
        do {
            System.out.print("CLI or GUI? (C/g)");
            String gameType = stdinReader.nextLine();
            if (gameType.isEmpty() || gameType.equals("C") || gameType.equals("c")) this.gameType = GameTypeEnum.CLI;
            else if (gameType.equals("G") || gameType.equals("g")) this.gameType = GameTypeEnum.GUI;
        } while (this.gameType == null);

        // Account Login
        List<Account> accounts;
        Account selectedAccount = null;
        InputTranslator inputTranslator = new InputTranslator(this.gameType);
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
        Character selectedCharacter = null;
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

        gameGrid = Grid.generateGrid(5, 5, selectedCharacter);
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
                    System.out.println("Congratulations! You have killed an enemy and gained 10XP!");
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

    }
}
