package tictactoe;

public class MoveScore {
    int move;
    int score;

    public MoveScore(int move, int score) {
        this.move = move;
        this.score = score;
    }

    public int getMove() {
        return move;
    }

    public int getScore() {
        return score;
    }
}
