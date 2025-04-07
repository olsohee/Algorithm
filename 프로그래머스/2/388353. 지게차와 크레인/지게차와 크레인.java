import java.util.*;

class Solution {
    
    char[][] map;
    boolean[][] visited;
    int n, m;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        map = new char[n + 2][m + 2];
        visited = new boolean[n + 2][m + 2];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                map[i][j] = storage[i - 1].charAt(j - 1);
            }
        }
        
        for (String request : requests) {
            
            // 지게차 사용
            if (request.length() == 1) {
                useForkLift(request.charAt(0));
            }
            
            // 크레인 사용
            if (request.length() == 2) {
                useCrain(request.charAt(0));
            }
        }
        
        int answer = 0;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (!visited[i][j]) answer++;
            }
        }
        
        return answer;
    }
    
    private void useForkLift(char alp) {
        
        List<Node> deliveryList = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        boolean[][] visitedForBfs = new boolean[n + 2][m + 2];
        // 외부에서 bfs 시작
        for (int i = 0; i <= n + 1; i++) {
            que.add(new Node(i, 0));
            que.add(new Node(i, m + 1));
            visitedForBfs[i][0] = true;
            visitedForBfs[i][m + 1] = true;
        }
        for (int i = 0; i <= m + 1; i++) {
            que.add(new Node(0, i));
            que.add(new Node(n + 1, i));
            visitedForBfs[0][i] = true;
            visitedForBfs[n + 1][i] = true;
        }
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (ny <= 0 || ny > n + 1 || nx <= 0 || nx > m + 1) {
                    continue;
                }
                
                if (visitedForBfs[ny][nx]) {
                    continue;
                }
                
                // 이미 출고한 컨테이너인 경우, 계속 탐색
                if (visited[ny][nx]) { 
                    que.add(new Node(ny, nx));
                    visitedForBfs[ny][nx] = true;
                }
                
                // 출고하지 않았으며 출고 가능한 컨테이너인 경우, 출고 표시
                if (!visited[ny][nx] && map[ny][nx] == alp) { 
                    deliveryList.add(new Node(ny, nx));
                } 
            }
        }
        
        for (int i = 0; i < deliveryList.size(); i++) {
            Node node = deliveryList.get(i);
            visited[node.y][node.x] = true;
        }
    }
    
    private void useCrain(char alp) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (!visited[i][j] && map[i][j] == alp) {
                    visited[i][j] = true;
                }
            }
        }
    }
    
    class Node {
        
        int y, x;
        
        public Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}