package tictactoe;

import tictactoe.player.Player;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe {

    Board board;
    List<Player> players;
    public static final String BLANK = " ";
    public static final String PLAYER1 = "X";
    public static final String PLAYER2 = "O";

    private static final Integer[][] WINNING_POSITIONS = {{0, 1, 2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public TicTacToe() {
        board = Board.fromString(BLANK.repeat(9));  // init empty board
        players = new ArrayList<>();
    }

    @Override
    public String toString() {
        return board.toString();
    }

    public boolean isVacant(Coordinate nextCoord) {
        return board.isVacant(nextCoord);
    }

    public void setPlayerMove(Coordinate coord, Player player) {
        board.setSymbolValue(coord.toIndex(), player.getSymbol());
    }

    private Player getNextPlayer() {
        int player1Count = 0;
        int player2Count = 0;
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        for (String s: board.getSymbolValues()) {
            if (s.equals(player1.toString())) {
                player1Count++;
            } else if (s.equals(player2.toString())) {
                player2Count++;
            }
        }
        return player1Count > player2Count ? player2 : player1;
    }


    public GameState getState() {

        if (board.isWinning(players.get(0).getSymbol())) {
            return GameState.XWins;
        } else if (board.isWinning(players.get(1).getSymbol())) {
            return GameState.OWins;
        } else if (board.isDraw()) {
            return GameState.Draw;
        }
        return GameState.NotFinished;
    }

    public void addPlayer(Player player) {
        if (players.size() > 2) {
            throw new IllegalStateException("Only 2 players allowed");
        }

        if (players.size() == 0) {
            player.setSymbol(PLAYER1);
        } else if (players.size() == 1) {
            player.setSymbol(PLAYER2);
        }
        players.add(player);
        player.setGame(this);
    }

    public void play() {
        System.out.println(this);
        while (getState() == GameState.NotFinished) {
            Player player = getNextPlayer();
            player.makeMove();
            System.out.println(this);
        }
        System.out.println(getState());
    }

    public boolean isWinningMove(int i, Player player) {
        int k, m, n;
        for (Integer[] indices: WINNING_POSITIONS) {
            k = indices[0];
            m = indices[1];
            n = indices[2];
            String s1 = getSymbol(k);
            String s2 = getSymbol(m);
            String s3 = getSymbol(n);
            Boolean term1 = s1.equals(s2) & s1.equals(player.toString()) & n == i;
            Boolean term2 = s1.equals(s3) & s1.equals(player.toString()) & m == i;
            Boolean term3 = s2.equals(s3) & s2.equals(player.toString()) & k == i;
            if (term1 || term2 || term3) {
                return true;
            }
        }
        return false;
    }

    private String getSymbol(int i) {
        return board.getSymbolValue(i);
    }

    public Player getOtherPlayer(Player player) {
        return player==players.get(0) ? players.get(1) : players.get(0);
    }

    public Board getBoard() {
        return board;
    }

    public List<Integer> getAvailableMove() {
        return board.getAvailableMove();
    }
}
