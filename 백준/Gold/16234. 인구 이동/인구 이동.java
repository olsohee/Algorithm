import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int L;
    static int R;
    static int[][] map;
    static boolean[][] visited;
    static int answer = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean isMove;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i <N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[N][N];
            isMove = false;
            // bfs
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }

            // 한 번도 안움직인 경우 끝내
            if (isMove) {
                answer++;
            } else {
                break;
            }
        }

        System.out.println(answer);
    }

    private static void bfs(int i, int j) {

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(i, j));
        visited[i][j] = true;

        List<Node> list = new ArrayList<>();
        int peopleCnt = map[i][j];
        list.add(new Node(i, j));

        // bfs로 한 덩이 찾기
        while (!que.isEmpty()) {
            Node n = que.poll();

            for (int k = 0; k < 4; k++) {
                int ny = n.y + dy[k];
                int nx = n.x + dx[k];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx]) continue;
                int diff = Math.abs(map[ny][nx] - map[n.y][n.x]);
                if (diff >= L && diff <= R) {
                    visited[ny][nx] = true;
                    que.add(new Node(ny, nx));
                    list.add(new Node(ny, nx));
                    peopleCnt += map[ny][nx];
                }
            }
        }

        if (list.size() > 1) {
            // 움직이기
            move(peopleCnt, list);
            isMove = true;
        }
    }

    private static void move(int peopleCnt, List<Node> list) {
        int value = peopleCnt / list.size();
        for (Node node : list) {
            map[node.y][node.x] = value;
        }
    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
