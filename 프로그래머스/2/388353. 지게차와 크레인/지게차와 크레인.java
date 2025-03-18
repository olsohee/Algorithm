import java.util.*;

class Solution {
    
    int y, x;
    char[][] map;
    boolean[][] visited;
    int[] dy = {1, -1, 0, 0};
    int[] dx = {0, 0, 1, -1};
    List<int[]> outlines = new ArrayList<>();
    
    public int solution(String[] storage, String[] requests) {
        y = storage.length + 2;
        x = storage[0].length() + 2;
        map = new char[y][x];
        visited = new boolean[y][x];
        
        for (int i = 0; i < storage.length; i++) {
            for (int j = 0; j < storage[i].length(); j++) {
                map[i + 1][j + 1] = storage[i].charAt(j);
            }
        }
        
        // 컨테이너 꺼내기
        for (String request : requests) {
            char container = request.charAt(0);
            // 1. 지게차로 꺼내기
            if (request.length() == 1) {
                // 가장자리 분류
                outlines = new ArrayList<>();
                for (int i = 0; i < y; i++) {
                    for (int j = 0; j < x; j++) {
                        if (i == 0 || i == y - 1 || j == 0 || j == x - 1) {
                            bfs(i, j);
                        } 
                    }
                }
                
                // 가장자리에서 접근
                for (int[] outline : outlines) {
                    for (int i = 0; i < 4; i++) {
                        int ny = dy[i] + outline[0];
                        int nx = dx[i] + outline[1];
                        if (ny < 0 || ny >= y || nx < 0 || nx >= x) continue;
                        if (map[ny][nx] == container && !visited[ny][nx]) {
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
            
            // 2. 크레인으로 꺼내기
            if (request.length() == 2) {
                for (int i = 0; i < y; i++) {
                    for (int j = 0; j < x; j++) {
                        if (map[i][j] == container && !visited[i][j]) {
                            visited[i][j] = true;
                        }
                    }
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i < y - 1; i++) {
            for (int j = 1; j < x - 1; j++) {
                if (!visited[i][j]) answer++;
            }
        }
        
        return answer;
    }
    
    private void bfs(int nowY, int nowX) {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] bfsVisited = new boolean[y][x];
        que.add(new int[]{nowY, nowX});
        bfsVisited[nowY][nowX] = true;
        outlines.add(new int[]{nowY, nowX});
        
        while (!que.isEmpty()) {
            int[] now = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now[0];
                int nx = dx[i] + now[1];
                if (ny < 0 || ny >= y || nx < 0 || nx >= x) continue;
                if (visited[ny][nx] && !bfsVisited[ny][nx]) {
                    bfsVisited[ny][nx] = true;
                    que.add(new int[]{ny, nx});
                    outlines.add(new int[]{ny, nx});
                }
            }
        }
    }
}