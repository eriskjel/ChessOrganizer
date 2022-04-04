package ntnu.idatt2001.k2g9.tournament;

import ntnu.idatt2001.k2g9.player.Player;

public class Match {
    private Player player1;
    private Player player2;
    private Player winner;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    public Match(){}

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int playersInitialized(){
        if (player1 == null && player2 == null){
            return 0;
        }
        else if (player1 == null|| player2 == null){
            return 1;
        }
        else{
            return 2;
        }
    }

    public Player getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        return "Match{" +
                "player1=" + player1.getName() +
                ", player2=" + player2.getName() + "}";
    }
}
