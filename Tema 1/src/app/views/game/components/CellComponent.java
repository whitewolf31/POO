package app.views.game.components;

import app.Game;
import app.core.Cell;
import app.types.CellEnum;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CellComponent extends JPanel {
    private Cell associatedCell;

    private Game game;

    public CellComponent(Cell associatedCell) {
        this.associatedCell = associatedCell;
        game = Game.getInstance();
        setSize(new Dimension(50, 50));
    }

    public void drawCell() {
        try {
            BufferedImage imageToAdd = null;
            if (associatedCell.getCoords().equals(game.selectedCharacter.getCoords())) {
                imageToAdd = ImageIO.read(new File("assets/player.png"));
            } else if (!associatedCell.getVisited()) {
                imageToAdd = ImageIO.read(new File("assets/question.png"));
            } else if (associatedCell.getType() == CellEnum.SHOP) {
                imageToAdd = ImageIO.read(new File("assets/shop.png"));
            } else if (associatedCell.getType() == CellEnum.ENEMY) {
                imageToAdd = ImageIO.read(new File("assets/enemy.jpg"));
            }
            if (imageToAdd != null) {
                JLabel photoLabel = new JLabel(new ImageIcon(imageToAdd));
                add(photoLabel);
            }
            setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reDrawCell() {
        removeAll();
        revalidate();
        repaint();
        drawCell();
    }
}
