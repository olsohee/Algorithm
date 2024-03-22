import java.util.*;

class Solution {
    
    List<String> list = new ArrayList<>();
    String[][] tickets;
    boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        
        this.tickets = tickets;
        this.visited = new boolean[tickets.length];
        
        // ICN에서 출발하여 dfs
        dfs("ICN", "ICN", 0);
        
        // 방문하는 공항 경로 (알파벳 순서가 앞서도록)
        Collections.sort(list);
        
        return list.get(0).split(" ");
    }
    
    public void dfs(String start, String route, int cnt) {
        
        if (cnt == tickets.length) {
            list.add(route);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            String[] ticket = tickets[i];
            if (start.equals(ticket[0]) && !visited[i]) {
                visited[i] = true;
                dfs(ticket[1], route + " " + ticket[1], cnt + 1);
                visited[i] = false;
            }
        }
    }
}