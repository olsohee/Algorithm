import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int[] e : edge) {
            list.get(e[0]).add(e[1]);
            list.get(e[1]).add(e[0]);
        }
        
        // 다익스트라
        Queue<Node> que = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[1] = 0;
        que.add(new Node(1, 0));
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            
            for (int next : list.get(now.end)) {
                if (dist[next] > now.cost + 1) {
                    dist[next] = now.cost + 1;
                    que.add(new Node(next, dist[next]));
                }
            }
        }
        
        int maxCost = 0;
        int cnt = 0;
        
        for (int i = 2; i <= n; i++) {
            if (maxCost < dist[i]) {
                maxCost = dist[i];
                cnt = 1;
            } else if (maxCost == dist[i]) cnt++;
        }
        return cnt;
    } 
    
    class Node {
        
        int end;
        int cost;
        
        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}