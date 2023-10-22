
import java.io.*;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static int M;
    static int[][] map;
    static int year;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {

            // 한덩이인지 아닌지 확인
            int result = countIsland();
            if(result >= 2) {
                break;
            } else if(result == 0) {
                year = 0;
                break ;
            }

            year++;
            map = minus();

        }

        System.out.println(year);
    }

    public static int countIsland() {

        boolean[][] visited = new boolean[N][M];
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    dfs(i, j, visited);
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static void dfs(int y, int x, boolean[][] visited) {

        visited[y][x] = true;

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= M || ny < 0 || ny >= N) {
                continue;
            }

            if(map[ny][nx] != 0 && !visited[ny][nx]) {
                dfs(ny, nx, visited);
            }
        }
    }

    public static int[][] minus() {

        boolean[][] visited = new boolean[N][M];
        int[][] cpyMap = new int[N][M];
        int y = 0;
        int x = 0;
        outer:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    y = i;
                    x = j;
                    break outer;
                }
            }
        }

        minusDfs(y, x, visited, cpyMap);
        return cpyMap;
    }

    public static void minusDfs(int y, int x, boolean[][] visited, int[][] cpyMap) {

        visited[y][x] = true;
        int minusCnt = findMinusCnt(y, x);

        if(map[y][x] >= minusCnt) {
            cpyMap[y][x] = map[y][x] - minusCnt;
        } else {
            cpyMap[y][x] = 0;
        }

        // 근처 탐색
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= M || ny < 0 || ny >= N) {
                continue;
            }

            if(map[ny][nx] != 0 && !visited[ny][nx]) {
                minusDfs(ny, nx, visited, cpyMap);
            }
        }
    }

    public static int findMinusCnt(int y, int x) {
        int minusCnt = 0;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || nx >= M || ny < 0 || ny >= N) {
                continue;
            }

            if(map[ny][nx] == 0) {
                minusCnt++;
            }
        }
        return minusCnt;
    }
}
