import java.util.*;

class Solution {
    
    int m, n;
    char[][] map;
    boolean isFinish = true;
    int answer = 0; // 지워진 블록 수
    
    public int solution(int m, int n, String[] board) {
        this.m = m;
        this.n = n;
        map = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        
        while (true) {
            isFinish = true;
            bfs();
            if (isFinish) break;
        }
        
        return answer;
    }
    
    private void bfs() {
        boolean[][] mark = new boolean[m][n];
        for (int i = 0; i <= m - 2; i++) {
            for (int j = 0; j <= n - 2; j++) {
                if (map[i][j] == 'X') continue;
                char c = map[i][j];
                if (map[i + 1][j] == c && map[i][j + 1] == c && map[i + 1][j + 1] == c) {
                    isFinish = false;
                    
                    mark[i][j] = mark[i + 1][j] = mark[i][j + 1] = mark[i + 1][j + 1] = true;
                }
            }
        }
        
        // 지워질 블록(마크 표시) answer 값 반영
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mark[i][j]) {
                    answer++;
                }
            }
        }
        
        // 블록 지우기
        for (int i = 0; i < n; i++) {
            Stack<Character> st = new Stack<>();
            for (int j = 0; j < m; j++) {
                if (mark[j][i]) continue;
                st.push(map[j][i]);
            }
            
            int idx = m - 1;
            while (!st.isEmpty()) {
                map[idx][i] = st.pop();
                idx--;
            }
            for ( ; idx >= 0; idx--) {
                map[idx][i] = 'X';
            }
        }
    }
}