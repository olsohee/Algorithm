import java.util.*;

class Solution {
           int[][] map;
        boolean[] visited;
        int answer;
        int startIdx = 0;
        int endIdx;
        int n;

        public int solution(String begin, String target, String[] words) {

            n = words.length + 1;
            visited = new boolean[n + 1];
            map = new int[n + 1][n + 1];

            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(target)) {
                    endIdx = i + 1;
                }
            }
            // word에 target이 없는 경우
            if (endIdx == 0) return 0;

            // 그래프 채우기
            for (int i = 0; i < words.length; i++) {
                if (canConvert(begin, words[i])) {
                    map[0][i + 1] = 1;
                    map[i + 1][0] = 1;
                }
            }

            for (int i = 0; i < words.length; i++) {
                for (int j = i + 1; j < words.length; j++) {
                    if (canConvert(words[i], words[j])) {
                        map[i + 1][j + 1] = 1;
                        map[j + 1][i + 1] = 1;
                    }
                }
            }
            // 최단거리 구하기 (dfs)
            dfs(0, 0);
            return answer;
        }

        private boolean canConvert(String begin, String word) {
            int sameCnt = 0;
            for (int i = 0; i < begin.length(); i++) {
                if (begin.charAt(i) == word.charAt(i)) sameCnt++;
            }
            return sameCnt == begin.length() - 1;
        }

        private void dfs(int startIdx, int cnt) {
            if (startIdx == endIdx) {
                answer = cnt;
                return;
            }
            visited[startIdx] = true;

            for (int i = 0; i <= n; i++) {
                if (map[startIdx][i] == 1 && !visited[i]) {
                    dfs(i, cnt + 1);
                }
            }
        }
}