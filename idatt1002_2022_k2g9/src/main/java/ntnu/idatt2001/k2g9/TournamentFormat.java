package ntnu.idatt2001.k2g9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Navid Muradi
 * @project idatt1002_2022_k2g9
 */
public class TournamentFormat {

    public static ArrayList<Match> createRounds(String format , PlayerRegistry players) {
        ArrayList<Match[]> layout;
        if (format.equals("Knock-Out"))
        {
            ArrayList<Player> participants = players.getPlayers();
            Collections.shuffle(participants);

            int log2base = (int) (Math.log(participants.size()) / Math.log(2));

            int noOfPreliminaryMatches = participants.size() - (int) Math.pow(2,log2base);

            Match[] preliminaryRound = new Match[noOfPreliminaryMatches];
            Match[] firstRound = new Match[(int) Math.pow(2 , log2base-1)];

            int participantIndex = 0;
            for (int i = 0 ; i < noOfPreliminaryMatches ; i += 2){
                preliminaryRound[i/2] = new Match(participants.get(participants.get(participants[participantIndex]), participants+1);
                participantIndex++;
            }

            for(int n = noOfPreliminaryMatches ; n < firstRound.length ; n += 2){

            }

        }

        return matches;
    }
}
