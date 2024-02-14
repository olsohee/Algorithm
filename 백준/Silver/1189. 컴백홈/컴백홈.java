import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R;
    static int C;
    static int K;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        // 왼쪽 아래에서 오른쪽 위로 이동하기
        dfs(R - 1, 0, 1);

        System.out.println(answer);
    }

    private static void dfs(int y, int x, int count) {
        if (count == K) {
            // K번 이동했을 때 위치가 오른쪽 위여야 함
            if (y == 0 && x == C - 1) {
                answer++;
            }
            return;
        }

        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (nx < 0 || nx >= C || ny < 0 || ny >= R) continue;
            if (visited[ny][nx]) continue;
            if (map[ny][nx] == 'T') continue;

            // 이동하기
            dfs(ny, nx, count + 1);
            visited[ny][nx] = false; // 초기화해주기!!
        }
    }
}