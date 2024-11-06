import java.util.*;

class Solution {
    
    public int solution(String[] board) {
        char[][] map = new char[3][3];
        for (int i = 0; i < 3; i++) {
            char[] arr = board[i].toCharArray();
            for (int j = 0; j < 3; j++) {
                map[i][j] = arr[j];
            }
        }
        
        // 1. O개수와 X개수가 같거나, O개수가 1개 더 많아야 함
        int oCnt = 0;
        int xCnt = 0;
        int emptyCnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 'O') {
                    oCnt++;
                } else if (map[i][j] == 'X') {
                    xCnt++;
                } else {
                    emptyCnt++;
                }
            }
        }
        
        if (oCnt == 0 && xCnt == 0) {
            return 1;
        }
        
        if (oCnt < xCnt || Math.abs(oCnt - xCnt) >= 2) {
            return 0;
        }
        
        
        // 2. 0명 또는 한명만 이겨야 함 (둘 다 이기면 X)
        int oWinCnt = 0;
        int xWinCnt = 0;
        // 가로 
        for (int i = 0; i < 3; i++) {
            char flag = map[i][0];
            boolean isWin = true;
            for (int j = 1; j < 3; j++) {
                if (flag != map[i][j]) {
                    isWin = false;
                    break;
                } 
            }
            
            if (isWin) {
                if (flag == 'O') {
                    oWinCnt++;
                }
                if (flag == 'X') {
                    xWinCnt++;
                }
            }
        }
        
        // 세로 
        for (int i = 0; i < 3; i++) {
            char flag = map[0][i];
            boolean isWin = true;
            for (int j = 1; j < 3; j++) {
                if (flag != map[j][i]) {
                    isWin = false;
                    break;
                } 
            }
            
            if (isWin) {
                if (flag == 'O') {
                    oWinCnt++;
                }
                if (flag == 'X') {
                    xWinCnt++;
                }
            }
        }
        // 대각선
        if (map[0][0] == map[1][1] && map[1][1] == map[2][2]) {
            if (map[0][0] == 'O') {
                oWinCnt++;
            }
            if (map[0][0] == 'X') {
                xWinCnt++;
            }
        }
        
        if (map[0][2] == map[1][1] && map[1][1] == map[2][0]) {
            if (map[0][2] == 'O') {
                oWinCnt++;
            }
            if (map[0][2] == 'X') {
                xWinCnt++;
            }
        }
        System.out.println(oWinCnt + ", " + xWinCnt);
        
        if (oWinCnt > 0 && xWinCnt > 0) {
            return 0; 
        }
        
        if (oWinCnt >= 1) {
            System.out.println("!");
            if (oCnt == xCnt) return 0;
            // if (emptyCnt > 0) return 0;
            // else return 1;
        }
        
        if (xWinCnt >= 1) {
            if (oCnt != xCnt) return 0;
        }
        
        return 1;
    }
}