import java.util.*;

class Solution {
    
    public int solution(int n, int[][] edge) {
        
        List<List<Node>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int[] e : edge) {
            int start = e[0];
            int end = e[1];
            list.get(start).add(new Node(end, 1));
            list.get(end).add(new Node(start, 1));
        }
        
        // 1번 노드를 시작으로 다익스트라 
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        Queue<Node> que = new PriorityQueue<>();
        que.add(new Node(1, 0));
        dist[1] = 0;
        
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
        
        // dist 배열에서 가장 큰 값의 노드 번호 찾기
        int max = 0;
        int maxCnt = 0;
        for (int i = 1; i <= n; i++) {
            if (max < dist[i]) {
                max = dist[i];
                maxCnt = 1;
            } else if (max == dist[i]) {
                maxCnt++;
            }
        }
        
        // 1번 노드로부터 가장 멀리 떨어진 노드의 개수
        return maxCnt;
    }
    
    public class Node implements Comparable<Node> {
        int end, cost;
        public Node (int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
        
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}