class Solution {
    public int[] solution(int[] sequence, int k) {
        // sum에 누적합 값 저장
        int n = sequence.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + sequence[i];
        }
        
        // 투포인터를 통해 합이 k인 부분 수열 구하기
        int start = 0;
        int end = 1;
        int answerLen = Integer.MAX_VALUE;
        int answer[] = new int[2];
        while (start != end && end <= n) {
            if (sum[end] - sum[start] == k) {
                if (answerLen > end - start) {
                    answerLen = end - start;
                    answer[0] = start;
                    answer[1] = end - 1;
                }
                start++;
                continue;
            }
            
            if (sum[end] - sum[start] > k) {
                start++;
            } else {
                end++;
            }
        }
        
        return answer;
    }
}