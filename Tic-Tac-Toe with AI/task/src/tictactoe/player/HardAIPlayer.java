package tictactoe.player;

import tictactoe.Board;
import tictactoe.Coordinate;
import tictactoe.MoveScore;
import tictactoe.TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class HardAIPlayer extends Player{

    public HardAIPlayer() {
        super();
    }

    @Override
    public void makeMove() {
        int bestMove = minimax(game.getBoard(), getSymbol(), 0);
        System.out.println("Making move level \"hard\"");
        game.setPlayerMove(Coordinate.fromIndex(bestMove), this);
    }

    private int minimax(Board board, String currentSymbol, int depth) {

        String otherSymbol = getOppositeSymbol(currentSymbol);
        String maximizeSymbol = getSymbol();

        // base case: board finished
        if (board.isWinning(otherSymbol)) {
            return otherSymbol.equals(maximizeSymbol) ? 10 : -10;
        }

        // recursive
        List<Integer> availableMoves = board.getAvailableMove();
        List<MoveScore> moveScores = new ArrayList<>();

         // draw - no available index
        if (availableMoves.isEmpty()) {
            return 0;
        }

        int score;
        Board newBoard;
        for (int nextIndex: availableMoves) {
            newBoard = board.copy();
            newBoard.setSymbolValue(nextIndex, currentSymbol);
            score = minimax(newBoard, otherSymbol, depth+1);
            moveScores.add(new MoveScore(nextIndex, score));
        }
        MoveScore bestMoveScore;
        if (currentSymbol.equals(maximizeSymbol)) {
            bestMoveScore = getMaxIndex(moveScores);
        } else {
            bestMoveScore = getMinIndex(moveScores);
        }
        // first call -> index, otherwise -> return score
        return depth == 0 ? bestMoveScore.getMove() : bestMoveScore.getScore();
    }

    private MoveScore getMinIndex(List<MoveScore> scores) {
        MoveScore result = scores.get(0);
        for (MoveScore x: scores) {
            if (result.getScore() > x.getScore()) {
                result = x;
            }
        }
        return result;
    }

    private MoveScore getMaxIndex(List<MoveScore> scores) {
        MoveScore result = scores.get(0);
        for (MoveScore x: scores) {
            if (result.getScore() < x.getScore()) {
                result = x;
            }
        }
        return result;
    }

    private String getOppositeSymbol(String symbol) {
        return symbol.equals(TicTacToe.PLAYER1) ? TicTacToe.PLAYER2 : TicTacToe.PLAYER1;
    }
}
