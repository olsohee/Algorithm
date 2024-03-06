import java.util.*;

class Solution {
     
        String[][] tickets;
        List<String> answerList = new ArrayList<>();
        boolean[] usedTicket;

        public String[] solution(String[][] tickets) {
            this.tickets = tickets;
            usedTicket = new boolean[tickets.length];

            // ICN에서 출발
            dfs("ICN", "ICN", 0);
            Collections.sort(answerList);
            return answerList.get(0).split(" ");
        }

        private void dfs(String start, String route, int cnt) {
            if (cnt == tickets.length) {
                answerList.add(route);
                return;
            }

            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i][0].equals(start) && !usedTicket[i]) {
                    usedTicket[i] = true;
                    dfs(tickets[i][1], route + " " + tickets[i][1], cnt + 1);
                    usedTicket[i] = false;
                }
            }
        }
}