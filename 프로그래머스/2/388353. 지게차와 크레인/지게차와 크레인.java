import java.util.*;

class Solution {
    
    int y, x;
    char[][] map;
    boolean[][] visited;
    List<int[]> list = new ArrayList<>(); // 테두리 리스트
    int[] dy = {0, 0, -1, 1};
    int[] dx = {1, -1, 0, 0};
    
    public int solution(String[] storage, String[] requests) {
        y = storage.length;
        x = storage[0].length();
        map = new char[y][x];
        visited = new boolean[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                map[i][j] = storage[i].charAt(j);
            }
        }
        
        int answer = 0;
        for (String request : requests) {
            char alp = request.charAt(0);
            list = new ArrayList<>();
            // 1. 기본 방법으로 꺼내기: 테두리에서 bfs 시작
            if (request.length() == 1) {
                for (int i = 0; i < y; i++) {
                    for (int j = 0; j < x; j++) {
                        // 테두리인 경우, bfs시작
                        if (i == 0 || i == y - 1 || j == 0 || j == x - 1) {
                            // if (map[i][j] == alp) {
                            //     visited[i][j] = true;
                            // }
                            if (visited[i][j]) {
                                bfs(i, j, alp);
                            }
                        }
                    }
                }
                
                for (int i = 0; i < y; i++) {
                    for (int j = 0; j < x; j++) {
                        if (i == 0 || i == y - 1 || j == 0 || j == x - 1) {
                            if (map[i][j] == alp) {
                                visited[i][j] = true;
                            }
                        }
                    }
                }
                for (int[] outline : list) {
                    for (int i = 0; i < 4; i++) {
                        int ny = dy[i] + outline[0];
                        int nx = dx[i] + outline[1];
                        if (ny < 0 || ny >= y || nx < 0 || nx >= x) continue;
                        if (map[ny][nx] == alp && !visited[ny][nx]) {
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
            
            // 2특별 방법으로 꺼내기: 전체 돌면서 꺼내기
            if (request.length() == 2) {
                for (int i = 0; i < y; i++) {
                    for (int j = 0; j < x; j++) {
                        if (!visited[i][j] && map[i][j] == alp) {
                            visited[i][j] = true;
                        }
                    }
                }
            }
        }
    
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                // String str = (visited[i][j]) ? "X" : "O";
                // System.out.print(str + " ");
                if (!visited[i][j]) answer++;
            }
            // System.out.println();
        }
        return answer; // 남은 컨테이너 수
    }
    
    private void bfs(int startY, int startX, char alp) {
        Queue<int[]> que = new LinkedList<>();
        boolean[][] nowVisited = new boolean[y][x];
        que.add(new int[]{startY, startX});
        list.add(new int[]{startY, startX});
        
        nowVisited[startY][startX] = true;
        
        while (!que.isEmpty()) {
            int[] now = que.poll();
        
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now[0];
                int nx = dx[i] + now[1];
                if (ny < 0 || ny >= y || nx < 0 || nx >= x) continue;
                if (!nowVisited[ny][nx] && visited[ny][nx]) {
                    que.add(new int[]{ny, nx});
                    list.add(new int[]{ny, nx});
                    nowVisited[ny][nx] = true;
                }
            }
        }
    }
}