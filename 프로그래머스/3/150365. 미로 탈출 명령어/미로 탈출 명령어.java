import java.util.ArrayList;
import java.util.List;

class Solution {

    char[] dist = {'d', 'l', 'r', 'u'};
    int[] dx = {1, 0, 0, -1}; // 세로
    int[] dy = {0, -1, 1, 0}; // 가로
    int n; int m;
    int x; int y;
    int r; int c;
    int k;
    List<char[]> answerList = new ArrayList<>();
    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        this.n = n; this.m = m;
        this.x = x; this.y = y;
        this.r = r; this.c = c;
        this.k = k;

        dfs(0, new char[k], x, y);

        if(answerList.size() == 0) {
            return "impossible";
        } else {
            char[] chars = answerList.get(0);
            String answer = "";
            for (char ch : chars) {
                answer += ch;
            }
            return answer;
        }
    }

    // a: 현재 세로 위치
    // b: 현재 가로 위치
    private void dfs(int depth, char[] chars, int a, int b) {
        // 답 하나 찾았으면 탐색X
        if(answerList.size() >= 1) {
            return;
        }

        // (남은 움직임 수 - 거리)가 홀수인 경우 탐색X
        if(((k - depth) - getDistance(a, b)) % 2 != 0) {
            return;
        }

        // (남은 움직임 수 < 현재 위치와 탈출구의 거리)인 경우 탐색X
        if((k - depth) < getDistance(a, b)) {
            return;
        }

        if(depth == k) {
            if(a == r && b == c) {
                answerList.add(chars);
            }
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + a;
            int ny = dy[i] + b;

            if(nx <= 0 || ny <= 0 || nx > n || ny > m) {
                continue;
            }

            char[] cloneChars = chars.clone();
            cloneChars[depth] = dist[i];
            dfs(depth + 1, cloneChars, nx, ny);
        }
    }

    private int getDistance(int a, int b) {
        return Math.abs(a - r) + Math.abs(b - c);
    }
}