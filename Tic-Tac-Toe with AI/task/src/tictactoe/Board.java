package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    String[] table;

    private Board() {
        table = new String[9];
        Arrays.fill(table, TicTacToe.BLANK);
    }

    public static Board fromString(String symbolSeq) {
        Board board = new Board();
        board.setTable(symbolSeq);
        return board;
    }

    private void setTable(String symbolSeq) {
        table = symbolSeq.split("");
    }

    public String toStringSequence() {
        StringBuilder builder = new StringBuilder();
        for (String s: table) {
            builder.append(s);
        }
        return builder.toString();
    }

    public Board copy() {
        return Board.fromString(toStringSequence());
    }

    public boolean isDraw() {
        int blankCount = 0;
        for (String s: table) {
            if (s.equals(TicTacToe.BLANK)) {
                blankCount++;
            }
        }
        return !isWinning(TicTacToe.PLAYER1) & !isWinning(TicTacToe.PLAYER2) & blankCount==0;
    }

    public boolean isWinning(String playerSymbol) {
        if (match(0, 1, 2, playerSymbol) ||
            match(3, 4, 5, playerSymbol) ||
            match(6, 7, 8, playerSymbol) ||
            match(0, 3, 6, playerSymbol) ||
            match(1, 4, 7, playerSymbol) ||
            match(2, 5, 8, playerSymbol) ||
            match(0, 4, 8, playerSymbol) ||
            match(2, 4, 6, playerSymbol)) {
            return true;
        }
        return false;
    }

    private boolean match(int i, int j, int k, String playerSymbol) {
        return getSymbolValue(i).equals(playerSymbol) & //
               getSymbolValue(j).equals(playerSymbol) & //
               getSymbolValue(k).equals(playerSymbol);
    }

    public String getSymbolValue(int index) {
        return table[index];
    }

    public void setSymbolValue(int index, String playerSymbol) {
        table[index] = playerSymbol;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---------\n");
        for (int row = 1; row < 4; row++) {
            stringBuilder.append("| ");
            for (int col = 1; col < 4; col++) {
                Coordinate coord = new Coordinate(row, col);
                stringBuilder.append(table[coord.toIndex()]).append(" ");
            }
            stringBuilder.append("|\n");
        }
        stringBuilder.append("---------\n");
        return stringBuilder.toString();
    }

    public boolean isVacant(Coordinate coordinate) {
        return table[coordinate.toIndex()].equals(TicTacToe.BLANK);
    }

    public String[] getSymbolValues() {
        return table;
    }


    public List<Integer> getAvailableMove() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (isVacant(Coordinate.fromIndex(i))) {
                result.add(i);
            }
        }
        return result;
    }
}
