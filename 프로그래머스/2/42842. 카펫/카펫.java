class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        for (int len = 1; len <= yellow; len++) {
            // len=세로, wid=가로
            if (yellow % len != 0) {
                continue;
            }
            
            int wid = yellow / len;
            if (len > wid) {
                continue;
            }
            
            int borderCnt = len * 2 + wid * 2 + 4;
            if (borderCnt == brown) {
                answer[0] = wid + 2;
                answer[1] = len + 2;
                break;
            }
        }
        
        return answer;
    }
}