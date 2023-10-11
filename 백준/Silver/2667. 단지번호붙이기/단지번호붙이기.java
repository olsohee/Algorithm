
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * 1. 아이디어
     * - DFS
     * - 그래프 돌면서 1인 경우에 DFS 시작
     * - DFS 시작하면, 갯수 체크, 방문 테크, 사방 돌면서 그래프 내이면서 1이면서 방문 안한 경우에 또 DFS 시작
     *
     * 2. 시간 복잡도
     * - DFS: O(V+E)
     *
     * 3. 자료구조
     * - 그래프, 방문 그래프
     * - 출력을 위한 리스트
     */

    static int N;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static StringTokenizer st;
    static BufferedReader br;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> result = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) throws IOException {

        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        // 그래프 탐색하며, 방문 안했으면서 1인 경우 DFS 시작
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] == 1 && !visited[i][j]) {
                    count = 1;
                    visited[i][j] = true;
                    dfs(i, j);
                    result.add(count);
                }

            }
        }

        // 결과값 정렬
        Collections.sort(result);
        System.out.println(result.size());
        for(int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    static void dfs(int y, int x) {

        // 4방면 탐색하며, 방문 안했으면서 1인 경우 개수 세기
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }

            if(visited[ny][nx] || map[ny][nx] != 1) {
                continue;
            }

            visited[ny][nx] = true;
            count++;
            dfs(ny, nx);
        }
    }
}