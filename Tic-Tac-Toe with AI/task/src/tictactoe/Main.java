package tictactoe;

import tictactoe.player.Player;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // easy, medium, hard, user, exit
        String command = getInputCommand();
        while (!command.equals("exit")) {
            playOneGame(command);
            command = getInputCommand();
        }
    }

    private static void playOneGame(String command) {

        String[] choices = command.split(" ");
        try {
            TicTacToe game = new TicTacToe();
            Player firstPlayer = Player.fromString(choices[0]);
            Player secondPlayer = Player.fromString(choices[1]);
            game.addPlayer(firstPlayer);
            game.addPlayer(secondPlayer);
            game.play();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Bad parameters!");
        }
    }

    private static String getInputCommand() {
        String command;
        System.out.print("Input command: > ");
        command = scanner.nextLine();
        return command.replace("start", "").trim();
    }
}
