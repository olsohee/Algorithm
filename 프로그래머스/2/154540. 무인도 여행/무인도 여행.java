import java.util.*;

class Solution {
    
    int yLen;
    int xLen;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    int[][] map;
    boolean[][] visited;
    List<Integer> answerList = new ArrayList<>();
    
    public int[] solution(String[] maps) {
        
        // bfs로 탐색하기 (10000 + 40000)

        yLen = maps.length;
        xLen = maps[0].length();
        
        visited = new boolean[yLen][xLen];
        map = new int[yLen][xLen];
        
        for (int i = 0; i < yLen; i++) {
            String[] arr = maps[i].split("");
            for (int j = 0; j < xLen; j++) {
                if (arr[j].equals("X")) continue;
                map[i][j] = Integer.parseInt(arr[j]);
            }
            
        }
        
        // bfs
        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        
        // 오름차순으로 정렬 (무인도가 없으면 -1을 배열에 담아 반환)
        if (answerList.size() == 0) {
            answerList.add(-1);
        }
        Collections.sort(answerList);
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
    
    public void bfs(int y, int x) {
        
        int sum = 0;
        
        Queue<Node> que = new LinkedList<>();
        visited[y][x] = true;
        sum += map[y][x];
        que.add(new Node(y, x));
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || ny >= yLen || nx < 0 || nx >= xLen) {
                    continue;
                }
                if (map[ny][nx] == 0 || visited[ny][nx]) {
                    continue;
                }
                visited[ny][nx] = true;
                que.add(new Node(ny, nx));
                sum += map[ny][nx];
            }
        }
        
        answerList.add(sum);
    }
    
    public class Node {
        
        int y, x;
        
        public Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
        
    }
}