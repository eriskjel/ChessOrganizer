package ntnu.idatt2001.k2g9.tournament;

import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;

import java.util.*;

/**
 * Class for creating tournament brackets of a given format.
 * When the bracket is created it will be sent into tournament class to be handled.
 */

public class TournamentFormat {
    /**
     * Static method to be called when creating tournament brackets.
     * The entire bracket is filled in as much as possible and is then handled by Tournament class.
     * @param format   The format of the bracket that should be created.
     * @param players  The players to participate in the bracket.
     * @return  The tournament bracket.
     */
    public static ArrayList<Match[]> createBracket(String format , PlayerRegistry players) {
        ArrayList<Match[]> layout = new ArrayList<>();

        if (format.equals("Knock-Out"))
        {
            //Creates and shuffles the players to take part in the tournament.
            ArrayList<Player> participants = new ArrayList<>();
            participants.addAll(players.getPlayers());
            Collections.shuffle(participants);

            //Calculating the number of rounds there will be, excluding preliminary round.
            int log2base = (int) (Math.log(participants.size()) / Math.log(2));

            //Calculates how many preliminary matches should be created.
            int noOfPreliminaryMatches = participants.size() - (int) Math.pow(2,log2base);

            //Creates match array with size equal to the number of preliminary matches.
            Match[] preliminaryRound = new Match[noOfPreliminaryMatches];

            //Fills the preliminary round with the shuffled arraylist of players until it is filled.
            for (int i = 0 ; i < noOfPreliminaryMatches ; i++){
                preliminaryRound[i] = new Match(participants.get(0) , participants.get(1));
                participants.remove(0);
                participants.remove(0);
            }

            //Fills in the first index of the layout arrayList with the preliminary round.
            layout.add(preliminaryRound);

            //Fills in the bracket with arrays of matches for each round, starting from first index.
            //ArrayList of match arrays for 10 players would be this layout:
            //{(Preliminary) Matches[2], (FirstRound) Matches[4], (semi-finals) Matches[2], (finals) Matches[1]}.
            Match[] tempHolder;
            for (int round = 1 ; round <= log2base ; round++) {
                tempHolder = new Match[(int) Math.pow(2 , log2base - round)];
                Arrays.fill(tempHolder, new Match());
                for (int i = 0; i < tempHolder.length; i++) {
                    tempHolder[i] = new Match();
                }

                layout.add(round, tempHolder);
            }

            //Fills in the first round of the tournament with the remaining players from bottom matches and up.
            for(int n = layout.get(1).length-1 ; n >= noOfPreliminaryMatches/2 ; n--) {
                if (participants.size() > 1){
                    layout.get(1)[n] = new Match(participants.get(0) , participants.get(1));
                    participants.remove(0);
                    participants.remove(0);
                } //If remaning player count is odd, one player will not have a pair to match up with.
                else {//This is taken into consideration so that the player will instead match with a winner from preliminaries.
                    layout.get(1)[n].setPlayer2(participants.get(0));
                    participants.remove(0);
                }
            }
        }gi1qq
        if (format.equals("Round-robin")) {

            //creates a list of participants and shuffles it
            ArrayList<Player> participants = new ArrayList<>(players.getPlayers());
            Collections.shuffle(participants);
            int numOfPlayers = participants.size();
            String[] evenPairs;
            // checks if numbers of players is an even number
            // if it's an odd number it adds a dummy called "Bye"
            if (numOfPlayers % 2 == 0) {
                evenPairs = new String[numOfPlayers - 1];
                for (int i = 0; i < numOfPlayers - 1; i++) {
                    evenPairs[i] = participants.get(i + 1).getName();
                }
            } else {
                evenPairs = new String[numOfPlayers];
                for (int i = 0; i < numOfPlayers - 1; i++) {
                    evenPairs[i] = participants.get(i + 1).getName();
                }
                evenPairs[participants.size() - 1] = "Bye";
            }

            // sorts and sets up the tournament format
            int pairSize = evenPairs.length; // an even number
            int total = pairSize; // rounds needed to complete tournament
            int halfSize = (pairSize + 1) / 2;
            int count = 0;
            int totNumberOfMatches = (total*(total-1)) / 2;
            ArrayList<Player> tempPlayers = new ArrayList<>();
            Match[] rounds = new Match[totNumberOfMatches];
            for (int round = total - 1; round >= 0; round--) {
                int playerIdx = round % pairSize;
                if (!evenPairs[playerIdx].equals("Bye")) {
                    tempPlayers.add(participants.get(0));
                    for (Player player : participants
                    ) {
                        if (player.getName().equals(evenPairs[playerIdx])) {
                            tempPlayers.add(player);
                        }
                    }
                }
                for (int i = 1; i < halfSize; i++) {
                    int player1 = (round + i) % pairSize;
                    int player2 = (round + pairSize - i) % pairSize;
                    if (!evenPairs[player1].equals("Bye") && !evenPairs[player2].equals("Bye")) {
                        for (Player player : participants) {
                            if (player.getName().equals(evenPairs[player1])) tempPlayers.add(player);
                            if (player.getName().equals(evenPairs[player2])) tempPlayers.add(player);
                        }
                    }
                }
            }
            for (int i = 0; i < totNumberOfMatches; i++) {
                rounds[i] = new Match(tempPlayers.get(0), tempPlayers.get(1));
                tempPlayers.remove(0);
                tempPlayers.remove(0);
            }
            layout.add(rounds);
        }
        return layout;
    }
}
