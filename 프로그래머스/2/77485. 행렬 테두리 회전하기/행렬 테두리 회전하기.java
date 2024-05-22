import java.util.*;

class Solution {
    
    List<Integer> answer = new ArrayList<>();
    int[][] map;
    
    public int[] solution(int rows, int columns, int[][] queries) {
   
        map = new int[rows + 1][columns + 1];
        
        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = num++;
            }
        }
            
        // 회전
        for (int[] q : queries) {
            move(q[0], q[1], q[2], q[3]);
        }
        
        return answer
            .stream()
            .mapToInt(i -> i)
            .toArray();
    }
    
     public void move(int y1, int x1, int y2, int x2) {
         int temp = map[y1][x1];
         int min = map[y1][x1];
         
         for (int i = y1; i < y2; i++) {
             map[i][x1] = map[i + 1][x1];
             min = Math.min(min, map[i + 1][x1]);
         }
         
         for (int i = x1; i < x2; i++) {
             map[y2][i] = map[y2][i + 1];
             min = Math.min(min, map[y2][i + 1]);
         }
         
         for (int i = y2; i > y1; i--) {
             map[i][x2] = map[i - 1][x2];
             min = Math.min(min, map[i - 1][x2]);
         }
         
         for (int i = x2; i > x1; i--) {
             map[y1][i] = map[y1][i - 1];
             min = Math.min(min, map[y1][i - 1]);
         }
         map[y1][x1 + 1] = temp;
         
         // 회전 후 이동한 숫자 중 최솟값 리스트에 담기
         answer.add(min);
     }
}