import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static boolean[][] visited = new boolean[30][30];
    static double[] percent = new double[4];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static double result;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        percent[0] = Integer.parseInt(st.nextToken()) * 0.01;
        percent[1] = Integer.parseInt(st.nextToken()) * 0.01;
        percent[2] = Integer.parseInt(st.nextToken()) * 0.01;
        percent[3] = Integer.parseInt(st.nextToken()) * 0.01;

        dfs(0, 15, 15, 1);
        System.out.println(result);
    }

    private static void dfs(int cnt, int y, int x, double total) {
        // 완성한 경우
        if (cnt == n) {
            result += total;
            return;
        }

        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (nx < 0 || nx >= 30 || ny < 0 || ny >= 30 || visited[ny][nx]) {
                continue;
            }
            dfs(cnt + 1, ny, nx, total * percent[i]);
            visited[ny][nx] = false;
        }
    }
}