class Solution {
    public int solution(int[][] sizes) {
         // 명함의 개수
            int cnt = sizes.length;

            // 각 명함의 최댓값, 최솟값을 보관하는 배열
            int[] maxNum = new int[cnt];
            int[] minNum = new int[cnt];

            // 지갑의 가로 길이, 세로 길이
            int length = 0;
            int height = 0;
            
            for(int[] card : sizes) {
                length = Math.max(length, Math.max(card[0], card[1]));
                height = Math.max(height, Math.min(card[0], card[1]));
            }
            
            return length * height;
        
    }
}