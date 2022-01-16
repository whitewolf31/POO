package app.shared;

import app.Game;
import app.types.GameTypeEnum;
import app.views.interfaces.SwingLogger;

import javax.swing.*;
import java.awt.*;

public class Logger {
    private static Logger instance = null;

    private Game game;

    private SwingLogger loggerComponent;

    private JLabel defaultLabel;

    private Logger() {
        game = Game.getInstance();
        defaultLabel = new JLabel();
    }

    public static Logger getInstance() {
        if (instance == null) instance = new Logger();

        return instance;
    }

    public void setLoggerComponent(SwingLogger loggerComponent) {
        this.loggerComponent = loggerComponent;
    }

    public void logMessage(String message, boolean addNewLine, Color textColor, Font font) {
        if (game.gameType == GameTypeEnum.CLI) {
            if (addNewLine) {
                System.out.println(message);
            } else {
                System.out.print(message);
            }
        } else {
            loggerComponent.addLog(message, textColor, font);
        }
    }

    public void logMessage(String message, boolean addNewLine, Color textColor) {
        logMessage(message, addNewLine, textColor, defaultLabel.getFont());
    }

    public void logMessage(String message, boolean addNewLine) {
        logMessage(message, addNewLine, defaultLabel.getForeground(), defaultLabel.getFont());
    }
}
