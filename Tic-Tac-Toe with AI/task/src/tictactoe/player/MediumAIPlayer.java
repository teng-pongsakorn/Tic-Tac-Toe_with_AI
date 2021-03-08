package tictactoe.player;

import tictactoe.Coordinate;

import java.util.*;

public class MediumAIPlayer extends Player{

    Random random;

    public MediumAIPlayer() {
        random = new Random();
    }

    @Override
    public void makeMove() {
        List<Integer> indices = game.getAvailableMove();
        int move;

        move = getWinningMove(indices);
        if (move == -1) {
            move = getLosingMove(indices);
        }
        if (move == -1) {
            move = getRandomMove(indices);
        }
        game.setPlayerMove(Coordinate.fromIndex(move), this);
    }

    private int getRandomMove(List<Integer> indices) {
        int i = random.nextInt(indices.size());
        return indices.get(i);
    }

    private int getLosingMove(List<Integer> indices) {
        for (int i: indices) {
            if (game.isWinningMove(i, game.getOtherPlayer(this))) {
                return i;
            }
        }
        return -1;
    }

    private int getWinningMove(List<Integer> indices) {
        for (int i: indices) {
            if (game.isWinningMove(i, this)) {
                return i;
            }
        }
        return -1;
    }
}
