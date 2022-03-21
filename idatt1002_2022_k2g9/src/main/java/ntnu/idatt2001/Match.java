package ntnu.idatt2001;

public class Match {
    private Player player1;
    private Player player2;
    private int result;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void setResult(int result) {
        this.result = result;
    }

}
