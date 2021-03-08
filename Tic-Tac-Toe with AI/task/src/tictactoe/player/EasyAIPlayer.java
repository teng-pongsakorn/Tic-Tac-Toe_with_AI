package tictactoe.player;

import tictactoe.Coordinate;

import java.util.Random;

public class EasyAIPlayer extends Player {

    Random random;

    public EasyAIPlayer() {
        random = new Random();
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"easy\"");
        Coordinate nextCoord = getRandomCoordinate();
        game.setPlayerMove(nextCoord, this);
    }

    private Coordinate getRandomCoordinate() {
        int i;
        while (true) {
            i = random.nextInt(9);
            Coordinate coordinate = Coordinate.fromIndex(i);
            if (game.isVacant(coordinate)) {
                return coordinate;
            }
        }
    }
}
