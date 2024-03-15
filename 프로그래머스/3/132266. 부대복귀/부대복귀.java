import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        List<List<Node>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int start = road[0];
            int end = road[1];
            list.get(start).add(new Node(end, 1));
            list.get(end).add(new Node(start, 1));
        }
        
        // destination을 기준으로 다익스트라
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        Queue<Node> que = new PriorityQueue<>();
        dist[destination] = 0;
        que.add(new Node(destination, 0));
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            if (visited[now.end]) continue;
            visited[now.end] = true;
            
            for (Node node : list.get(now.end)) {
                if (dist[node.end] > now.cost + node.cost) {
                    dist[node.end] = now.cost + node.cost;
                    que.add(new Node(node.end, dist[node.end]));
                }
            }
        }
        
        // 최단 시간 (복귀 불가능한 경우 -1 반환)
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            if (dist[sources[i]] == Integer.MAX_VALUE) {
                answer[i] = -1;
            } else {
                answer[i] = dist[sources[i]];
            }
        }
        return answer;
    }
    
    class Node implements Comparable<Node> {
        int end, cost;
        public Node (int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}