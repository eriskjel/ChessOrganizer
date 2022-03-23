package ntnu.idatt2001.k2g9;

import java.util.*;

/**
 * @author Navid Muradi
 * @project idatt1002_2022_k2g9
 */
public class TournamentFormat {

    public static ArrayList<Match[]> createRounds(String format , PlayerRegistry players) {
        ArrayList<Match[]> layout = new ArrayList<>();

        if (format.equals("Knock-Out"))
        {
            //Creates and shuffles the players to take part in the round.
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

            //Fills in the layout with arrays of matches for each round, starting from first index.
            //ArrayList of Arrays of matches for 10 players would be this layout:
            //{(Preliminary) Matches[2], (FirstRound) Matches[4], (semi-finals) Matches[2], (finals) Matches[1]}.
            Match[] tempHolder;
            for (int round = 1 ; round <= log2base ; round++) {
                tempHolder = new Match[(int) Math.pow(2 , log2base - round)];
                Arrays.fill(tempHolder, new Match());
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
        }
        return layout;
    }
}
