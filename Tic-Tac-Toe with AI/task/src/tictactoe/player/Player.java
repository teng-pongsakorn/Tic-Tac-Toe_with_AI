package tictactoe.player;

import tictactoe.TicTacToe;

public abstract class Player {

    TicTacToe game;
    String symbol;

    public Player() {
        super();
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    };

    public String getSymbol() {return symbol;}

    @Override
    public String toString() {
        return symbol;
    }

    public static Player fromString(String type) {
        if (type.equals("user")) {
            return new HumanPlayer();
        } else if (type.equals("easy")) {
            return new EasyAIPlayer();
        } else if (type.equals("medium")) {
            return new MediumAIPlayer();
        } else if (type.equals("hard")) {
            return new HardAIPlayer();
        }
        throw new IllegalArgumentException("Bad parameters!");
    }

    public abstract void makeMove();

    public void setGame(TicTacToe game) {
        this.game = game;
    };
}
