
import java.io.*;
import java.util.StringTokenizer;

/*
    - 그래프 돌면서 벽을 세울 수 있는 조합 구하기 (재귀)
    - 3개 세웠고, 해당 그래프를 복제해서 만든 후에 -> 바이러스 퍼지기 시작
    - 바이러스가 모두 퍼진 후에 그래프 탐색해서 안전지대의 개수 구하기

    시간 복잡도
    - 벽을 세울 수 있는 조합 구하기 : 2 ^ 3(세우는 벽 개수)
    - 각 조합에 대해서 바이러스 퍼지기
 */

public class Main {

    static int N; // 세로
    static int M; // 가로
    static int map[][];
    static boolean visited[][];
    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 3개의 벽 세우기 (조합 = dfs)
        dfs(0);

        System.out.println(answer);
    }

    public static void dfs(int cnt) {

        if(cnt == 3) {

            // 벽이 3개 세워진 경우, 바이러스 퍼지기
            // 각 조합마다 벽이 다르게 세워져 있으므로 배열 복사해서 사용
            int[][] cpyMap = new int[N][M];
            for(int i = 0; i < N; i++) {
                cpyMap[i] = map[i].clone();
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(cpyMap[i][j] == 2) {
                        virusDfs(i, j, cpyMap);
                    }
                }
            }

            // 바이러스 다 퍼진 후에 안전지대 구하기
            answer = Math.max(answer, findNotVirusCnt(cpyMap));

            return;
        }

        // 그래프 탐색하면서 벽 세우거나, 안세우거나
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                // 빈 칸, 즉 벽을 세울 수 있는 경우에
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void virusDfs(int y, int x, int[][] map) {


        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            // 그래프 밖인 경우
            if(nx < 0 || nx >= M || ny < 0 || ny >= N) {
                continue;
            }

            // 벽인 경우에만 퍼질 수 있음
            if(map[ny][nx] == 0) {
                // 바이러스 퍼지면 -1로 채우기
                map[ny][nx] = -1;
                virusDfs(ny, nx, map);
            }
        }
    }

    private static int findNotVirusCnt(int[][] map) {

        int cnt = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
