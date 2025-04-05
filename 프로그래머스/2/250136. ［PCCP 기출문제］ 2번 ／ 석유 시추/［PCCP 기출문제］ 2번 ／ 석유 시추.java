import java.util.*;

class Solution {
    
    int n, m;
    int[][] land;
    int[][] oilNumLand;
    Map<Integer, Integer> oilNumAndCntMap = new HashMap();
    boolean[][] visited;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
    
    private void bfs(int y, int x, int oilNum) {
        visited[y][x] = true;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        int cnt = 1;
        oilNumLand[y][x] = oilNum;
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                if (visited[ny][nx] || land[ny][nx] == 0) continue;
                
                visited[ny][nx] = true;
                oilNumLand[ny][nx] = oilNum;
                que.add(new Node(ny, nx));
                cnt++;
            }
        }
        
        oilNumAndCntMap.put(oilNum , cnt); 
    }
    
    public int solution(int[][] land) {
        n = land.length; // 세로
        m = land[0].length; // 가로
        this.land = land;
        oilNumLand = new int[n][m];
        visited = new boolean[n][m];
        
        // bfs로 석유 구역 체크
        int num = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, num++);
                }
            }
            
        }
        
//         for (int i : oilNumAndCntMap.keySet()) {
//             System.out.println(i + " -> " + oilNumAndCntMap.get(i));
//         }
        
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < m; j++) {
//                 System.out.print(oilNumLand[i][j] + " ");
//             }
//         System.out.println();
//         }
        
        int answer = 0;
        
        for (int i = 0; i < m; i++) {
            boolean[] visited = new boolean[num];
            int sum = 0; // 현재 열에서 발견하는 석유 수
            for (int j = 0; j < n; j++) {
                int oilNum = oilNumLand[j][i];
                if (oilNum == 0) continue;
                if (visited[oilNum]) continue;
                sum += oilNumAndCntMap.get(oilNum);
                visited[oilNum] = true;
            }
            
            answer = Math.max(answer, sum);
        }
        
    
        return answer;
    }
    
    class Node {
        int y, x;
        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}