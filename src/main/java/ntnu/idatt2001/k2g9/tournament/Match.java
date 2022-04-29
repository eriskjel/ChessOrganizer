package ntnu.idatt2001.k2g9.tournament;

import ntnu.idatt2001.k2g9.player.Player;

public class Match {
    private Player player1;
    private Player player2;
    private Player winner;

    /**
     * Class representing a match between two players.
     * @param player1
     * @param player2
     */
    /**
     * Simple constructor for Match.
     * @param player1
     * @param player2
     */
    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Constructor that creates an empty match.
     */
    public Match(){}

    /**
     * Sets the winner of the match.
     * @param winner
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    /**
     * Returns player 1.
     * @return
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Sets player 1.
     * @param player1
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    /**
     * Returns player 2.
     * @return
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Sets player 2.
     * @param player2
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * Returns a value that represents which of the players are initialized, or optionally none of them.
     * @return
     */
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

    /**
     * Returns the winner.
     * @return
     */
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
