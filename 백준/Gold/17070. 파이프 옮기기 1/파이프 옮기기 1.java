import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // bfs

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(1, 2, 1));
        int answer = 0;

        if (map[n][n] == 1) {
            System.out.println(0);
            return;
        }


        while (!que.isEmpty()) {
            Node now = que.poll();

            if (now.y == n && now.x == n) {
                answer++;
            }

            // 가로로 놓인 경우
            if (now.flag == 1) {
                // 오른쪽 이동
                if (now.x + 1 <= n) {
                    if (map[now.y][now.x + 1] == 0) {
                        que.add(new Node(now.y, now.x + 1, 1));
                    }
                }

                // 대각선 이동
                if (now.y + 1 <= n && now.x + 1 <= n) {
                    if (map[now.y + 1][now.x] == 0 && map[now.y + 1][now.x + 1] == 0 && map[now.y][now.x + 1] == 0) {
                        que.add(new Node(now.y + 1, now.x + 1, 3));
                    }
                }
            }

            // 세로로 놓인 경우
            if (now.flag == 2) {
                // 아래 이동
                if (now.y + 1 <= n) {
                    if (map[now.y + 1][now.x] == 0) {
                        que.add(new Node(now.y + 1, now.x, 2));
                    }
                }

                // 대각선 이동
                if (now.y + 1 <= n && now.x + 1 <= n) {
                    if (map[now.y + 1][now.x] == 0 && map[now.y + 1][now.x + 1] == 0 && map[now.y][now.x + 1] == 0) {
                        que.add(new Node(now.y + 1, now.x + 1, 3));
                    }
                }
            }

            // 대각선으로 놓인 경우
            if (now.flag == 3) {
                // 오른쪽 이동
                if (now.x + 1 <= n) {
                    if (map[now.y][now.x + 1] == 0) {
                        que.add(new Node(now.y, now.x + 1, 1));
                    }
                }

                // 아래 이동
                if (now.y + 1 <= n) {
                    if (map[now.y + 1][now.x] == 0) {
                        que.add(new Node(now.y + 1, now.x, 2));
                    }
                }

                // 대각선 이동
                if (now.y + 1 <= n && now.x + 1 <= n) {
                    if (map[now.y + 1][now.x] == 0 && map[now.y + 1][now.x + 1] == 0 && map[now.y][now.x + 1] == 0) {
                        que.add(new Node(now.y + 1, now.x + 1, 3));
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static class Node {
        int y, x;
        int flag;

        public Node(int y, int x, int flag) {
            this.y = y;
            this.x = x;
            this.flag = flag;
        }
    }
}
