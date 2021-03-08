package tictactoe.player;

import tictactoe.Coordinate;

import java.util.Scanner;

public class HumanPlayer extends Player {

    Scanner scanner;

    public HumanPlayer() {
        super();
        scanner = new Scanner(System.in);
    }

    @Override
    public void makeMove() {
        while (true) {
            System.out.print("Enter the coordinates: > ");
            try {
                Coordinate nextCoord = Coordinate.fromString(scanner.nextLine());
                if (game.isVacant(nextCoord)) {
                    game.setPlayerMove(nextCoord, this);
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
