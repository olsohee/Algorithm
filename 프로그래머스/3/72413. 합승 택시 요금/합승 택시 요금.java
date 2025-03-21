import java.util.*;

class Solution {
    
    int n;
    int[][] fares;
    List<List<Node>> list = new ArrayList<>();
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] fare : fares) {
            int node1 = fare[0];
            int node2 = fare[1];
            int cost = fare[2];
            list.get(node1).add(new Node(node2, cost));
            list.get(node2).add(new Node(node1, cost));
        }
        
        // s에서 각 노드로의 최소 비용
        int[] sCost = getCost(s);
        
        // a에서 각 노드로의 최소 비용
        int[] aCost = getCost(a);
        
        // b에서 각 노드로의 최소 비용
        int[] bCost = getCost(b);
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) { // s에서 i까지 합승
            int cost = sCost[i] + aCost[i] + bCost[i];
            answer = Math.min(answer, cost);
        }
        return answer;
    }
    
    private int[] getCost(int start) {
        // start에서 각 노드로 가는데 드는 최소 비용
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;
        
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        
        Queue<Node> que = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        que.add(new Node(start, 0));
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            // System.out.println();
            // System.out.println(now.end +"에서 출발");
            
            // now.end에서 다음 노드로 이동
            for (Node next : list.get(now.end)) {
                // System.out.println(next.end +"로 가기");
                // System.out.println(costs[next.end] + " vs " + (now.cost + next.cost));

                if (costs[next.end] > now.cost + next.cost) {
                    costs[next.end] = now.cost + next.cost;
                    visited[next.end] = true;
                    que.add(new Node(next.end, costs[next.end]));
                    // System.out.println("!" + costs[next.end]);
                    
                }
            }
        }
        
        // System.out.println(start +"부터 각 노드 간 비용");
        // for (int n : costs) {
        //     System.out.print(n + " ");
        // }
        // System.out.println();
        
        return costs;
    }
    
    private class Node {
        
        int end;
        int cost; // end까지 가는데 드는 최소 비용
        
        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }
}