
import java.io.*;
import java.util.*;

public class Main {

    static int M; // 세로
    static int N; // 가로
    static int K;
    static int[][] map;
    static boolean[][] visited;
    static int answer = 0;
    static int cnt = 0;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visited = new boolean[M][N];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 좌표 채우기
            int width = x2 - x1;
            int heigth = y2 - y1;
            for(int j = (M - y2); j < heigth + (M - y2); j++) {
                for(int k = x1; k < width + x1; k++) {
                    map[j][k] = 1;
                }
            }
        }

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 0 && !visited[i][j]) {
                    answer++;
                    cnt = 0;
                    dfs(i, j);
                    list.add(cnt);
                }
            }
        }
        Collections.sort(list);

        System.out.println(answer);
        for (Integer i : list) {
            System.out.print(i + " ");
        }
    }

    static void dfs(int y, int x) {

        cnt++;
        visited[y][x] = true;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            if(map[ny][nx] == 0 && !visited[ny][nx]) {
                dfs(ny, nx);
            }
        }
    }
}
