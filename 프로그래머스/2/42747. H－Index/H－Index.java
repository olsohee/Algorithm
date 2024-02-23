import java.util.*;

class Solution {
     public int solution(int[] citations) {

            int n = citations.length;
            Arrays.sort(citations);
            int max = citations[n - 1];
            for (int i = max; i >= 0; i--) {
                int cnt = 0;
                for (int j = 0; j < n; j++) {
                    if (citations[j] >= i) {
                        cnt++;
                    }
                }
                if (cnt >= i) {
                    return i;
                }
            }

            return 0;
        }
}