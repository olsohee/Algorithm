import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        
        List<List<Node>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList());
        }
        
        for (int[] e : edge) {
            int start = e[0];
            int end = e[1];
            list.get(start).add(new Node(end, 1));
            list.get(end).add(new Node(start, 1));
        }
        
        // 1번을 시작으로 다익스트라
        Queue<Node> que = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[1] = 0;
        que.add(new Node(1, 0));
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            
            if (visited[now.end]) continue;
            
            visited[now.end] = true;
            
            // now.end에서 출발
            for (Node node : list.get(now.end)) {
                if (dist[node.end] > now.cost + node.cost) {
                    dist[node.end] = now.cost + node.cost;
                    que.add(new Node(node.end, dist[node.end]));
                }
            }
        }
        
        // dist[i]: 1에서 i까지 가는데 걸리는 비용
        int max = 0;
        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if (dist[i] > max) {
                max = dist[i];
                answer = 0;
            }
            if (dist[i] == max) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public class Node implements Comparable<Node> {
        
        int end, cost;
        
        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}