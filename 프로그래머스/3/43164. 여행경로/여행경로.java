import java.util.*;

class Solution {
    
    List<String> answerList = new ArrayList<>();
    String[][] tickets;
    boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        this.visited = new boolean[tickets.length];
        
        dfs(0, "ICN", "ICN");
        Collections.sort(answerList);
        return answerList.get(0).split(", ");
    }
    
    public void dfs(int cnt, String start, String answer) {
        
        if (cnt == tickets.length) {
            answerList.add(answer);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (!start.equals(tickets[i][0])) continue;
            if (visited[i]) continue; // 이미 사용한 티켓이면 패스
            visited[i] = true;
            dfs(cnt + 1, tickets[i][1], answer + ", " + tickets[i][1]);
            visited[i] = false;
        }
    }
}