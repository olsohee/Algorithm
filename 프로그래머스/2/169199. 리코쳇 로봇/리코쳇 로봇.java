import java.util.*;

class Solution {
    
    int answer = -1;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    public int solution(String[] board) {
        
        int yLen = board.length;
        int xLen = board[0].length();
        
        // 기존의 bfs랑 똑같은데 다음 위치가 다른 것 뿐
        // 1. map에 저장하기
        int startY = 0;
        int startX = 0;
        String[][] map = new String[yLen][xLen];
        int[][] visited = new int[yLen][xLen];
        for (int i = 0; i < yLen; i++) {
            String[] arr = board[i].split("");
            for (int j = 0; j < arr.length; j++) {
                map[i][j] = arr[j];
                if (map[i][j].equals("R")) {
                    startY = i;
                    startX = j;
                }
            }
        }
        
        // 2. 시작점에서 bfs 시작
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(startY, startX));
        visited[startY][startX] = 1;
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            
            // 해당 위치가 도착지인 경우 끝내기
            if (map[now.y][now.x].equals("G")) {
                answer = visited[now.y][now.x] - 1;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                
                while (true) {
                    // 벽이거나 장애물이면 끝, 아니면 계속 가기
                    if (nx < 0 || nx >= xLen || ny < 0 || ny >= yLen || map[ny][nx].equals("D")) {
                        ny -= dy[i];
                        nx -= dx[i];
                        break;
                    }
                    ny += dy[i];
                    nx += dx[i];
                }
                
                // 이때 ny, nx가 다음 이동할 위치임
                // 방문하지 않은 경우에만 큐에 넣기
                if (visited[ny][nx] == 0) {
                    que.add(new Node(ny, nx));
                    visited[ny][nx] = visited[now.y][now.x] + 1;
                }
            }
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