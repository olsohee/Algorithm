import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static char[][] map = new char[5][5];
    static int answer = 0;
    static int[] choiced = new int[7];
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 1. 25개 중 7개 고르기 (조합 만들기)
        dfs(0, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int depth, int start, int yCnt) {
        if (yCnt == 4) {
            return;
        }

        if (depth == 7) {
            if (canAnswer()) answer++;
            return;
        }

        for (int i = start; i < 25; i++) {
            choiced[depth] = i;
            if (map[i / 5][i % 5] == 'Y') {
                dfs(depth + 1, i + 1, yCnt + 1);
            } else {
                dfs(depth + 1, i + 1, yCnt);
            }
        }
    }

    private static boolean canAnswer() {
        int y = choiced[0] / 5;
        int x = choiced[0] % 5;
        boolean[] visited = new boolean[7];
        visited[0] = true;
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));

        int cnt = 0;

        while (!que.isEmpty()) {
            Node now = que.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) continue;

                for (int j = 0; j < choiced.length; j++) {
                    if (visited[j]) continue;
                    if (choiced[j] / 5 == ny && choiced[j] % 5 == nx) {
                        visited[j] = true;
                        que.add(new Node(ny, nx));
                        break;
                    }
                }
            }
        }

        return cnt == 7;
    }

    private static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
