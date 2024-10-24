import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] map = new int[19][19];
    static int answer;
    static int[] answerNode = new int[2];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (map[i][j] == 1 || map[i][j] == 2) {
                    if (j == 0 || (j - 1 >= 0 && map[i][j - 1] != map[i][j])) {
                        dfs(i, j, i, j, 0, 1); // dir = 0 (오른쪽)
                    }
                    if (i == 0 || (i - 1 >= 0 && map[i - 1][j] != map[i][j])) {
                        dfs(i, j, i, j, 1, 1); // dir = 1 (아래)
                    }
                    if ((i == 0 || j == 0) || (j - 1 >= 0 && i - 1 >= 0 && map[i - 1][j - 1] != map[i][j])) {
                        dfs(i, j, i, j, 2, 1); // dir = 0 (오른쪽 아래 대각선)
                    }
                    if ((i == 18 || j == 0) || (j + 1 < 19 && i - 1 >= 0 && map[i + 1][j - 1] != map[i][j])) {
                        dfs(i, j, i, j, 3, 1); // dir = 0 (오른쪽 위 대각선)
                    }
                }
            }
        }

        System.out.println(answer);
        if (answer != 0) {
            System.out.print(answerNode[0] + " " + answerNode[1]);
        }
    }

    private static void dfs(int startY, int startX, int y, int x, int dir, int cnt) { // cnt = map[y][x] 포함한 갯수
        if (cnt == 5) {
            boolean canAnswer = false;
            // 여섯개가 아닌지 확인 후 답 반영
            if (dir == 0) {
                if (x + 1 < 19) {
                    if (map[y][x] != map[y][x + 1]) {
                        canAnswer = true;
                    }
                } else {
                    canAnswer = true;
                }
            }
            if (dir == 1) {
                if (y + 1 < 19) {
                    if (map[y][x] != map[y + 1][x]) {
                        canAnswer = true;
                    }
                } else {
                    canAnswer = true;
                }
            }
            if (dir == 2) {
                if (x + 1 < 19 && y + 1 < 19) {
                    if (map[y][x] != map[y + 1][x + 1]) {
                        canAnswer = true;
                    }
                } else {
                    canAnswer = true;
                }
            }
            if (dir == 3) {
                if (x + 1 < 19 && y - 1 >= 0) {
                    if (map[y][x] != map[y - 1][x + 1]) {
                        canAnswer = true;
                    }
                } else {
                    canAnswer = true;
                }
            }

            if (canAnswer) {
                answer = map[y][x];
                answerNode[0] = startY + 1;
                answerNode[1] = startX + 1;
            }
        }

        switch (dir) {
            case 0:
                if (x + 1 < 19 && map[y][x + 1] == map[y][x]) {
                    dfs(startY, startX, y, x + 1, dir, cnt + 1);
                }
                break;
            case 1:
                if (y + 1 < 19 && map[y + 1][x] == map[y][x]) {
                    dfs(startY, startX, y + 1, x, dir, cnt + 1);
                }
                break;
            case 2:
                if (x + 1 < 19 && y + 1 < 19 && map[y + 1][x + 1] == map[y][x]) {
                    dfs(startY, startX, y + 1, x + 1, dir, cnt + 1);
                }
                break;
            case 3:
                if (x + 1 < 19 && y - 1 >= 0 && map[y - 1][x + 1] == map[y][x]) {
                    dfs(startY, startX, y - 1, x + 1, dir, cnt + 1);
                }
        }
    }
}