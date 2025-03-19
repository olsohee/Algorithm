import java.util.*;

class Solution {
    
    int n, m;
    int[][] mark;
    
    public int solution(int[][] board, int[][] skill) {
        n = board.length; // 세로 길이
        m = board[0].length; // 가로 길이
        mark = new int[n][m];
        
        // 1. 스킬을 표시하기
        for (int[] s : skill) {
            int type = s[0];
            int y1 = s[1];
            int x1 = s[2];
            int y2 = s[3];
            int x2 = s[4];
            int degree = s[5];
            
            // 공격
            if (type == 1) {
                mark(y1, x1, y2, x2, degree * - 1);
            }
            // 회복
            if (type == 2) {
                mark(y1, x1, y2, x2, degree);
            }
        }
        
        // 2. 스킬을 표시한 mark 표를 누적합 하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } 
                if (i == 0) {
                    mark[i][j] = mark[i][j - 1] + mark[i][j];
                    continue;
                }
                if (j == 0) {
                    mark[i][j] = mark[i - 1][j] + mark[i][j];
                    continue;
                }
                
                mark[i][j] = mark[i - 1][j] + mark[i][j - 1] - mark[i - 1][j - 1] + mark[i][j];
            }
        }
        
        // 3. 누적합한 결과를 board에 반영하기
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += mark[i][j];
                if (board[i][j] > 0) answer++;
            }
        }
        
        return answer; // 최종적으로 파괴되지 않은 건물 수
    }
    
    private void mark(int y1, int x1, int y2, int x2, int degree) {
        mark[y1][x1] += degree;
        if (y2 + 1 < n && x2 + 1 < m) {
            mark[y1][x2 + 1] -= degree;
            mark[y2 + 1][x1] -= degree;
            mark[y2 + 1][x2 + 1] += degree;
        } else if (y2 + 1 < n) {
            mark[y2 + 1][x1] -= degree;
        } else if (x2 + 1 < m) {
            mark[y1][x2 + 1] -= degree;
        } 
    }
}