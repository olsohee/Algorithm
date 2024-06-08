class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        // 시작점
        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] != 0 || pickups[i] != 0) {
                answer += (i + 1) * 2;
                break;
            }
        }
        
        int d = cap;
        int p = cap;
        
        for (int i = n - 1; i >= 0; i--) {
            // System.out.println();
            // System.out.println("i = " + i);
            // System.out.println("d = " + d);
            // System.out.println("p = " + p);
            // System.out.println("answer = " + answer);
            d -= deliveries[i];
            p -= pickups[i];
            
            while (d < 0 || p < 0) {
                d += cap;
                p += cap;
                answer += (i + 1) * 2;
            }
        }
        
        return answer;
    }
}