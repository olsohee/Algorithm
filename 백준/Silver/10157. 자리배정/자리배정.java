import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int c;
    static int r;
    static int k;
    static int[][] map;
    static int[] dy = {-1, 0, 1, 0}; // 위, 오른쪽, 아래, 왼쪽
    static int[] dx = {0, 1, 0, -1};
    static int answerY = 0;
    static int answerX = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        k = Integer.parseInt(br.readLine());

        if (c * r < k) {
            System.out.println(0);
            return;
        }

        dfs(1, r - 1, 0, 0);
        System.out.println((answerX + 1) + " " + (r - answerY));
    }

    private static void dfs(int num, int y, int x, int dir) {
        if (num == k) {
            answerY = y;
            answerX = x;
            return;
        }
        map[y][x] = num;

        int ny = y + dy[dir];
        int nx = x + dx[dir];
        
        // 방향 전환이 필요한 경우
        if (ny < 0 || ny >= r || nx < 0 || nx >= c || map[ny][nx] != 0) {
            dir = (dir + 1) % 4;
            ny = y + dy[dir];
            nx = x + dx[dir];
        }

        dfs(num + 1, ny, nx, dir);
    }
}
