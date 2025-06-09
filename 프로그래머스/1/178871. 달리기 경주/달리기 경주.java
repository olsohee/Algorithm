import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }
        
        for (String callingName : callings) {
            int rank = map.get(callingName);
            map.put(players[rank], map.get(players[rank]) - 1);
            map.put(players[rank - 1], map.get(players[rank - 1]) + 1);
            players[rank] = players[rank - 1];
            players[rank - 1] = callingName;
        }
        
        return players;
    }
}