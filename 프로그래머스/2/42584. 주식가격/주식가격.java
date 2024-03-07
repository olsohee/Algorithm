class Solution {
    public int[] solution(int[] prices) {
        
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            int cnt = 0;
            int startNum = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] < startNum) {
                    cnt++;
                    break;
                }
                cnt++;
            }
            answer[i] = cnt;
        }
        return answer;
    }
}