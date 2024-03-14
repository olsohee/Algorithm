import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);    
        }
        
        for (int i = 0; i < callings.length; i++) {
            // map에서 해당 선수가 몇등인지 찾기
            String name1 = callings[i];
            int rank = map.get(name1);
            String name2 = players[rank - 1];
            
            // 배열에서 위치 변경
            players[rank - 1] = name1;
            players[rank] = name2;
            
            // 변경된 위치 map에 반영
            map.put(name1, rank - 1);
            map.put(name2, rank);
        }
        return players;
    }
}