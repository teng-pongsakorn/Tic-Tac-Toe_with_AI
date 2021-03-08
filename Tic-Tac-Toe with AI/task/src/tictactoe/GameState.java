package tictactoe;

public enum GameState {

    XWins("X wins"),
    OWins("O wins"),
    Draw("Draw"),
    NotFinished("Game not finished");

    String description;

    GameState(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
