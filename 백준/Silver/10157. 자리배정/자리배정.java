
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
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1, 0}; // 위, 오른쪽, 아래, 왼쪽
    static int[] dx = {0, 1, 0, -1};
    static int answerY = 0;
    static int answerX = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        visited = new boolean[r][c];
        k = Integer.parseInt(br.readLine());

        if (c * r < k) {
            System.out.println(0);
            return;
        }

        int num = 1;
        int y = r - 1;
        int x = 0;
        int dir = 0;

        while (num < k) {
            // 1. 현재 위치 방문
            visited[y][x] = true;

            // 2. 다음 위치찾기
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            // 방향 전환이 필요한 경우
            if (ny < 0 || ny >= r || nx < 0 || nx >= c || visited[ny][nx]) {
                dir = (dir + 1) % 4;
                ny = y + dy[dir];
                nx = x + dx[dir];
            }

            y = ny;
            x = nx;
            num++;
        }

        System.out.println((x + 1) + " " + (r - y));
    }
}