package tictactoe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coordinate {
    int row;
    int col;

    public Coordinate(int row, int col) {
        if (validRow(row) & validColumn(col)) {
            this.row = row;
            this.col = col;
        } else {
            throw new IllegalArgumentException("Coordinates should be from 1 to 3!");
        }
    }

    public static Coordinate fromString(String inputString) {
        String targetPattern = "\\b(\\d)\\s+(\\d)\\b";
        Pattern pattern = Pattern.compile(targetPattern);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.find()) {
            int row = Integer.parseInt(matcher.group(1));
            int col = Integer.parseInt(matcher.group(2));
            return new Coordinate(row, col);
        } else {
            throw new IllegalArgumentException("You should enter numbers!");
        }
    }

    public static Coordinate fromIndex(int i) {
        int row = i / 3;
        int col = i % 3;
        return new Coordinate(row + 1, col + 1);
    }

    private boolean validColumn(int col) {
        return col >= 1 & col <= 3;
    }

    private boolean validRow(int row) {
        return row >= 1 & row <= 3;
    }

    public int toIndex() {
        return (row - 1) * 3 + (col - 1);
    }
}
