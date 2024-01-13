// 시간 복잡도: O(N)
class Solution {

    public int solution(String name) {

        int answer = 0;
        int moveCount = name.length() - 1;

        for (int i = 0; i < name.length(); i++) {
            answer += Math.min(name.charAt(i) - 'A', 26 - (name.charAt(i) - 'A'));
            if (i < name.length() - 1 && name.charAt(i + 1) == 'A') {
                int endA = i + 1;
                while (endA < name.length() && name.charAt(endA) == 'A') {
                    endA++;
                }
                // endA: 연속된 A가 끝난 다음 인덱스
                moveCount = Math.min(moveCount, i * 2 + (name.length() - endA));
                moveCount = Math.min(moveCount, (name.length() - endA) * 2 + i);
            }
        }
        return answer + moveCount;
    }
}