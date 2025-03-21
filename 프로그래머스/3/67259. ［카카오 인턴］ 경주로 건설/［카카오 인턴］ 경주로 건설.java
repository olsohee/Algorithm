import java.util.*;

class Solution {
    
    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] board) {
        
        int n = board.length;
        int[][][] costs = new int[n][n][4];
        Queue<Node> que = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(costs[i][j], Integer.MAX_VALUE);
            }
        }
        
        // 0, 0을 시작으로 bfs
        que.add(new Node(0, 0, 1, 0));
        que.add(new Node(0, 0, 2, 0));
        costs[0][0][1] = 0;
        costs[0][0][2] = 0;
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            
            if (now.y == n - 1 && now.x == n - 1) {
                answer = Math.min(answer, now.cost);
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (ny < 0 || ny == n || nx < 0 || nx == n || board[ny][nx] == 1) { 
                    continue;
                } 
            
                if (now.dir == i) { // 직진
                    if (costs[ny][nx][i] > now.cost + 100) {
                        que.add(new Node(ny, nx, i, now.cost + 100));
                        costs[ny][nx][i] = now.cost + 100;
                    }
                   
                } else { // 코너 돌기
                    if (costs[ny][nx][i] > now.cost + 600) {
                        que.add(new Node(ny, nx, i, now.cost + 600));
                        costs[ny][nx][i] = now.cost + 600;
                        
                    }
                    
                }
            }
        }
        
        return answer;
    }
    
    private class Node {
        int y, x, dir, cost;
        
        public Node (int y, int x, int dir, int cost) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cost = cost;
        }
    }
}