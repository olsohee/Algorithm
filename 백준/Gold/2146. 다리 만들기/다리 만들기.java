import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        // 0. map 초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 각각의 섬을 bfs하면서 숫자로 표시하기 (2, 3, ...)
        int num = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    bfs(i, j, num++);
                }
            }
        }

        for (boolean[] arr : visited) {
            Arrays.fill(arr, false);
        }

        // 2. 다시 각각의 섬을 bfs하다가 가장자리이면, 바다 bfs하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(answer);
    }

    public static void seaBfs(int y, int x, int startNum) {
        Queue<Node> que = new LinkedList<>();
        int[][] seaVisited = new int[n][n];
        seaVisited[y][x] = 1;
        que.add(new Node(y, x));

        while (!que.isEmpty()) {
            Node now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (seaVisited[ny][nx] != 0) continue;

                // 다음 노드가 섬이면
                if (map[ny][nx] != 0) {
                    // 같은 섬이면 패스
                    if (map[ny][nx] == startNum) {
                        continue;
                    }
                    // 다른 섬이면 정답 갱신
                    else {
                        answer = Math.min(answer, seaVisited[now.y][now.x]);
                        return;
                    }
                }

                // 다음 노드가 바다이면 계속 진행
                if (map[ny][nx] == 0) {
                    seaVisited[ny][nx] = seaVisited[now.y][now.x] + 1;
                    que.add(new Node(ny, nx));
                }
            }
        }
    }

    public static void bfs(int y, int x) {
        Queue<Node> que = new LinkedList<>();
        visited[y][x] = true;
        que.add(new Node(y, x));

        while (!que.isEmpty()) {
            Node now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[ny][nx]) continue;

                // 다음 노드가 섬이면 계속 진행
                if (map[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    que.add(new Node(ny, nx));
                }

                // 다음 노드가 바다이면 바다 bfs하기
                if (map[ny][nx] == 0) {
                    seaBfs(ny, nx, map[now.y][now.x]);
                }
            }
        }
    }

    public static void bfs(int y, int x, int num) {
        Queue<Node> que = new LinkedList<>();
        visited[y][x] = true;
        map[y][x] = num;
        que.add(new Node(y, x));

        while (!que.isEmpty()) {
            Node now = que.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[ny][nx]) continue;

                // 다음 노드가 섬이면 계속 진행
                if (map[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    map[ny][nx] = num;
                    que.add(new Node(ny, nx));
                }
            }
        }
    }


    public static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
