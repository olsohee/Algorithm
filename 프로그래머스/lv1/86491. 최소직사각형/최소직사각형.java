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

            for(int i = 0; i < cnt; i++) {
                if(sizes[i][0] > sizes[i][1]) {
                    maxNum[i] = sizes[i][0];
                    minNum[i] = sizes[i][1];
                } else {
                    maxNum[i] = sizes[i][1];
                    minNum[i] = sizes[i][0];
                }
            }

            for(int i = 0; i < maxNum.length; i++) {
                length = Math.max(length, maxNum[i]);
            }

            for(int i = 0; i < minNum.length; i++) {
                height = Math.max(height, minNum[i]);
            }

            return length * height;
    }
}