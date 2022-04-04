package ntnu.idatt2001.k2g9.tournament;

import ntnu.idatt2001.k2g9.player.Player;
import ntnu.idatt2001.k2g9.player.PlayerRegistry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.IntStream;

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

            if(noOfPreliminaryMatches == 0)
                layout.remove(0);
        }

        if (format.equals("Round-Robin")) {
            ArrayList<Player> participants = new ArrayList<>(players.getPlayers());

            if (participants.size()%2 == 1)
                participants.add(new Player("Bye", 0));
            int matchesPerRound = participants.size()/2;

            int noOfRounds = participants.size()-1;

            //Fills in the layout with Match arrays of correct size containing empty Match objects.
            Match[] tempHolder;
            for (int round = 0 ; round < noOfRounds ; round++) {
                tempHolder = new Match[matchesPerRound];
                for (int i = 0; i < tempHolder.length; i++) {
                    tempHolder[i] = new Match();
                }

                layout.add(round, tempHolder);
            }

            ArrayList<Integer> participantOrder = new ArrayList<>(participants.size());
            for(int i = 0; i < participants.size(); i++){
                participantOrder.add(i);
            }

            for (int n = 0 ; n < noOfRounds ; n++ ){
                for (int i = 0 ; i < matchesPerRound ; i ++){
                    layout.get(n)[i] = new Match(participants.get(participantOrder.get(i)), participants.get(participantOrder.get(participants.size()-i-1)));
                }
                participantOrder.add(participantOrder.get(1));
                participantOrder.remove(1);
            }
        }

        if (format.equals("Swiss-System")){
            ArrayList<Player> participants = new ArrayList<>(players.getPlayers());

            if (participants.size() % 2 == 1)
                participants.add(new Player("Bye", 0));
            int matchesPerRound = participants.size()/2;

            //Calculating the number of rounds there will be.
            int log2base = (int) (Math.log(participants.size()) / Math.log(2));

            //Fills in the layout with Match arrays of correct size containing empty Match objects.
            Match[] tempHolder;
            for (int round = 0 ; round < log2base ; round++) {
                tempHolder = new Match[matchesPerRound];
                for (int i = 0; i < tempHolder.length; i++) {
                    tempHolder[i] = new Match();
                }

                layout.add(round, tempHolder);
            }

            ArrayList<Integer> participantOrder = new ArrayList<>(participants.size());
            for(int i = 0; i < participants.size(); i++){
                participantOrder.add(i);
            }

            for (int n = 0 ; n < log2base ; n++ ){
                for (int i = 0 ; i < matchesPerRound ; i ++){
                    layout.get(n)[i] = new Match(participants.get(participantOrder.get(i)), participants.get(participantOrder.get(participants.size()-i-1)));
                }
                participantOrder.add(participantOrder.get(1));
                participantOrder.remove(1);
            }
        }
        return layout;
    }

}
