import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int max = 0;
        int min = 0;
        for (int[] size : sizes) {
            int num = Math.max(size[0], size[1]);
            if (num > max) {
                max = num;
                min = Math.min(size[0], size[1]);
            }
            if (num == max) {
                min = Math.max(min, Math.min(size[0], size[1]));
            }
        }
        
        // 한쪽은 무조건 max, 반대쪽은 무조건 min 이상의 값
        for (int i = min; i <= max; i++) {
            if (canPut(max, i, sizes)) {
                return max * i;
            }
        }
        
        return -1;
    }
    
    public boolean canPut(int n1, int n2, int[][] sizes) {
        
        for (int[] size : sizes) {
            if (!(size[0] <= n1 && size[1] <= n2) && !(size[0] <= n2 && size[1] <= n1)) {
                return false;
            } 
        }
        System.out.println("!");
        return true;
    }
}