import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도:
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int r;
    static int c;
    static int[][] map;
    static boolean[] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        visited = new boolean[26];

        for (int i = 0; i < r; i++) {
            String input = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = input.charAt(j) - 'A';
            }
        }

        // 0,0부터 dfs
        dfs(0, 0, 1);

        System.out.println(answer);
    }

    private static void dfs(int y, int x, int count) {

        visited[map[y][x]] = true;
        answer = Math.max(answer, count);

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (nx < 0 || nx >= c || ny < 0 || ny >= r) {
                continue;
            }

            // map[ny][nx]의 알파벳을 이미 방문하지 않았을 때만
            if (!visited[map[ny][nx]]) {
                dfs(ny, nx, count + 1);
                // 재귀 나온 후에는 초기화
                visited[map[ny][nx]] = false;
            }
        }
    }
}