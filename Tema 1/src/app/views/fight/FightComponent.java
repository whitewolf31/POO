package app.views.fight;

import app.Game;
import app.entities.Enemy;
import app.errors.InvalidCommandException;
import app.player.Spell;
import app.player.inventory.Potion;
import app.shared.Logger;
import app.types.FightStatusEnum;
import app.views.end.EndComponent;
import app.views.fight.components.BarComponent;
import app.entities.Character;
import app.views.fight.components.FightLoggerComponent;
import app.views.game.GameComponent;
import app.views.shared.HoverMouseListener;
import app.views.shared.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

public class FightComponent extends JPanel {
    private static FightComponent instance = null;

    private Game game;

    private JLabel playerCurrentHpLabel;

    private BarComponent playerHpBar;

    private JLabel playerCurrentManaLabel;

    private BarComponent playerManaBar;

    private JLabel enemyCurrentHpLabel;

    private BarComponent enemyHpBar;

    private JLabel enemyCurrentManaLabel;

    private BarComponent enemyManaBar;

    private JPanel spellsPanel;

    private JPanel potionsPanel;

    private Character player;

    private Enemy enemy;

    private JLabel defaultLabel;

    private JButton goBackButton;

    private FightStatusEnum fightStatus;

    private FightComponent() {
        game = Game.getInstance();
        setLayout(null);
        defaultLabel = new JLabel();
        setSize(new Dimension(1000, 1000));
        startFight();
    }

    public static FightComponent getInstance() {
        if (instance == null) instance = new FightComponent();

        return instance;
    }

    public void startFight() {
        removeAll();
        revalidate();
        repaint();
        fightStatus = FightStatusEnum.ONGOING;

        JPanel titlePanel = new JPanel();
        titlePanel.setSize(new Dimension(980, 50));
        JLabel title = new JLabel("FIGHT");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(title);
        titlePanel.setBounds(10, 10, 980, 50);
        add(titlePanel);

        player = game.selectedCharacter;
        enemy = (Enemy) game.gameGrid.getCurrentCell().getCellElement();

        JPanel youPanel = new JPanel();
        JLabel you = new JLabel("You");
        you.setFont(new Font(title.getFont().getName(), Font.ITALIC, 24));
        you.setHorizontalAlignment(SwingConstants.CENTER);
        youPanel.add(you);
        youPanel.setBounds(10, 60, 300, 50);
        add(youPanel);

        playerCurrentHpLabel = new JLabel();
        playerCurrentHpLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN,20));
        playerCurrentHpLabel.setBounds(10, 110, 300, 50);
        playerCurrentHpLabel.setText("Current HP: " + player.getCurrentHP());
        add(playerCurrentHpLabel);

        playerHpBar = new BarComponent(player.getCurrentHP(), player.getMaxHP(), Color.GREEN);
        playerHpBar.setBounds(10, 165, 300, 30);
        add(playerHpBar);

        playerCurrentManaLabel = new JLabel();
        playerCurrentManaLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN,20));
        playerCurrentManaLabel.setBounds(10, 200, 300, 50);
        playerCurrentManaLabel.setText("Current Mana: " + player.getCurrentMana());
        add(playerCurrentManaLabel);

        playerManaBar = new BarComponent(player.getCurrentMana(), player.getMaxMana(), Color.BLUE);
        playerManaBar.setBounds(10, 255, 300, 30);
        add(playerManaBar);

        JPanel enemyPanel = new JPanel();
        JLabel enemyLabel = new JLabel("Enemy");
        enemyLabel.setFont(new Font(title.getFont().getName(), Font.ITALIC, 24));
        enemyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        enemyPanel.add(enemyLabel);
        enemyPanel.setBounds(690, 60, 300, 50);
        add(enemyPanel);

        enemyCurrentHpLabel = new JLabel();
        enemyCurrentHpLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN,20));
        enemyCurrentHpLabel.setBounds(690, 110, 300, 50);
        enemyCurrentHpLabel.setText("Current HP: " + enemy.getCurrentHP());
        add(enemyCurrentHpLabel);

        enemyHpBar = new BarComponent(enemy.getCurrentHP(), enemy.getMaxHP(), Color.GREEN);
        enemyHpBar.setBounds(690, 165, 300, 30);
        add(enemyHpBar);

        enemyCurrentManaLabel = new JLabel();
        enemyCurrentManaLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN,20));
        enemyCurrentManaLabel.setBounds(690, 200, 300, 50);
        enemyCurrentManaLabel.setText("Current Mana: " + enemy.getCurrentMana());
        add(enemyCurrentManaLabel);

        enemyManaBar = new BarComponent(enemy.getCurrentMana(), enemy.getMaxMana(), Color.BLUE);
        enemyManaBar.setBounds(690, 255, 300, 30);
        add(enemyManaBar);

        FightLoggerComponent fightLoggerComponent = new FightLoggerComponent();
        JScrollPane loggerScrollPane = new JScrollPane(fightLoggerComponent);
        Logger.getInstance().setLoggerComponent(fightLoggerComponent);
        loggerScrollPane.setBounds(350, 110, 300, 200);
        add(loggerScrollPane);

        JLabel spellLabel = new JLabel("Spells");
        spellLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN,20));
        spellLabel.setBounds(10, 290, 300, 50);
        add(spellLabel);

        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setHgap(10);
        spellsPanel = new JPanel(layout);
        spellsPanel.setBounds(10, 345, 980, 200);
        add(spellsPanel);
        drawSpells();

        JLabel potionLabel = new JLabel("Potions");
        potionLabel.setFont(new Font(title.getFont().getName(), Font.PLAIN,20));
        potionLabel.setBounds(10, 550, 300, 50);
        add(potionLabel);

        potionsPanel = new JPanel(layout);
        potionsPanel.setBounds(10, 605, 980, 200);
        add(potionsPanel);
        drawPotions();

        JButton normalAttack = new JButton("Normal Attack");
        normalAttack.setBounds(310, 320, 150, 30);
        normalAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fightStatus != FightStatusEnum.ONGOING) return;
                enemy.receiveDamage(player.getDamage());
                resetEnemy();
                checkAnyoneDead();
                if (fightStatus == FightStatusEnum.ONGOING) {
                    simulateEnemyAttack();
                    checkAnyoneDead();
                    if (fightStatus == FightStatusEnum.ENEMY_DIED) {
                        goBackButton.setVisible(true);
                        enemyDiedCoins();
                    } else if (fightStatus == FightStatusEnum.PLAYER_DIED) {
                        game.setCurrentView(new EndComponent("GG. You died"), "World of Marcel");
                    }
                }
            }
        });
        add(normalAttack);

        goBackButton = new JButton("Go Back");
        goBackButton.setBounds(465, 320, 150, 30);
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.setCurrentView(GameComponent.getInstance(), "World of Marcel");
            }
        });
        add(goBackButton);
    }

    public void resetPlayer() {
        playerCurrentHpLabel.setText("Current HP: " + player.getCurrentHP());
        playerHpBar.setCurrentValue(player.getCurrentHP());
        playerCurrentManaLabel.setText("Current Mana: " + player.getCurrentMana());
        playerManaBar.setCurrentValue(player.getCurrentMana());
    }

    public void resetEnemy() {
        enemyCurrentHpLabel.setText("Current HP: " + enemy.getCurrentHP());
        enemyHpBar.setCurrentValue(enemy.getCurrentHP());
        enemyCurrentManaLabel.setText("Current Mana: " + enemy.getCurrentMana());
        enemyManaBar.setCurrentValue(enemy.getCurrentMana());
    }

    private void drawSpells() {
        List<Spell> spellList = game.selectedCharacter.getSpellList();
        for (Spell spell: spellList) {
            RoundedPanel currentSpellPanel = new RoundedPanel(10);
            GridLayout layout = new GridLayout(3, 0);
            layout.setVgap(10);
            currentSpellPanel.setLayout(layout);
            currentSpellPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            JLabel spellName = new JLabel(spell.getClass().getSimpleName() + " Spell");
            JLabel spellCost = new JLabel("Mana: " + spell.getManaRequired());
            JLabel spellDamage = new JLabel("Damage: " + spell.getDamage());
            currentSpellPanel.add(spellName);
            currentSpellPanel.add(spellCost);
            currentSpellPanel.add(spellDamage);
            Color initialBg = currentSpellPanel.getBackground();
            currentSpellPanel.addMouseListener(new HoverMouseListener(initialBg, currentSpellPanel) {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (fightStatus != FightStatusEnum.ONGOING) return;
                    if (spell.getManaRequired() <= player.getCurrentMana()) {
                        player.useSpell(spell, enemy);
                        resetEnemyAndPlayer();
                        checkAnyoneDead();
                        if (fightStatus == FightStatusEnum.ONGOING) {
                            simulateEnemyAttack();
                            checkAnyoneDead();
                            if (fightStatus == FightStatusEnum.PLAYER_DIED) {
                                game.setCurrentView(new EndComponent("GG. You died"), "World of Marcel");
                            }
                        } else if (fightStatus == FightStatusEnum.ENEMY_DIED) {
                            goBackButton.setVisible(true);
                            revalidate();
                            repaint();
                            enemyDiedCoins();
                        }
                        spellsPanel.remove(currentSpellPanel);
                        spellsPanel.revalidate();
                        spellsPanel.repaint();
                    } else {
                        Logger.getInstance().logMessage("You don't have enough mana", false);
                    }
                }
            });
            spellsPanel.add(currentSpellPanel);
        }
    }

    public void drawPotions() {
        List<Potion> potionList = game.selectedCharacter.getInventory().getPotions();
        for (Potion potion: potionList) {
            RoundedPanel currentPotionPanel = new RoundedPanel(10);
            GridLayout layout = new GridLayout(3, 0);
            layout.setVgap(10);
            currentPotionPanel.setLayout(layout);
            currentPotionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            JLabel potionName = new JLabel(potion.getClass().getSimpleName());
            JLabel potionWeight = new JLabel("Weight: " + potion.getWeight());
            JLabel potionRegen = new JLabel("Regen: " + potion.getRegenValue());
            currentPotionPanel.add(potionName);
            currentPotionPanel.add(potionWeight);
            currentPotionPanel.add(potionRegen);
            Color initialBg = currentPotionPanel.getBackground();
            currentPotionPanel.addMouseListener(new HoverMouseListener(initialBg, currentPotionPanel) {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (fightStatus != FightStatusEnum.ONGOING) return;
                    potion.usePotion(player);
                    resetPlayer();
                    potionsPanel.remove(currentPotionPanel);
                    potionsPanel.revalidate();
                    potionsPanel.repaint();
                }
            });
            potionsPanel.add(currentPotionPanel);
        }
    }

    private void checkAnyoneDead() {
        if (enemy.getCurrentHP() <= 0) {
            Logger.getInstance().logMessage("You killed an enemy and received 10XP", false, Color.GREEN, new Font(defaultLabel.getFont().getName(), Font.ITALIC, defaultLabel.getFont().getSize()));
            fightStatus = FightStatusEnum.ENEMY_DIED;
        } else if (player.getCurrentHP() <= 0) {
            Logger.getInstance().logMessage("You died!", false, Color.RED, new Font(defaultLabel.getFont().getName(), Font.ITALIC, defaultLabel.getFont().getSize()));
            fightStatus = FightStatusEnum.PLAYER_DIED;
        }
    }

    private void simulateEnemyAttack() {
        Random rand = new Random();
        int chanceToUseAbility = rand.nextInt(4);
        boolean usedAbility = false;
        if (chanceToUseAbility == 0) {
            if (enemy.getSpellListSize() > 0) {
                int randomSpellIndex = rand.nextInt(enemy.getSpellListSize());
                Spell spellToUse = enemy.getSpellByIndex(randomSpellIndex);
                Logger.getInstance().logMessage("Enemy used " + spellToUse.getClass().getSimpleName() + " against you.", false);
                enemy.useSpell(spellToUse, player);
                usedAbility = true;
            }
        }
        if (!usedAbility) {
            Logger.getInstance().logMessage("Enemy used basic attack against you!", false);
            player.receiveDamage(enemy.getDamage());
        }
        resetEnemyAndPlayer();
        checkAnyoneDead();
        if (fightStatus != FightStatusEnum.ONGOING) {

        }
    }

    private void resetEnemyAndPlayer() {
        resetEnemy();
        resetPlayer();
    }

    private void enemyDiedCoins() {
        Random rand = new Random();
        int randNum = rand.nextInt(3) + 5;
        Logger.getInstance().logMessage("You received " + randNum + " coins!", false, Color.GREEN);
        player.getInventory().giveCoins(randNum);
    }
}
