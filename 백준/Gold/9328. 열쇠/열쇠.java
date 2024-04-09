import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String[][] map;
    static Set<String> keys;
    static int newKeyCnt = 0;
    static int openDoorCnt = 0;
    static int answer = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int h;
    static int w;

    public static void main(String[] args) throws IOException {

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            answer = 0;

            // map 초기화
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new String[h][w];
            for (int i = 0; i < h; i++) {
                String[] input = br.readLine().split("");
                for (int j = 0; j < w; j++) {
                    map[i][j] = input[j];
                }
            }

            // keys 초기화
            keys = new HashSet<>();
            String[] input = br.readLine().split("");
            for (int i = 0; i < input.length; i++) {
                keys.add(input[i]);
            }

            while (true) {
                newKeyCnt = 0;
                openDoorCnt = 0;

                // 가장자리 .에서부터 bfs 시작
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        if (i > 0 && i <= h - 2 && j > 0 && j <= w - 2) {
                            continue;
                        }

                        char c= map[i][j].charAt(0);
                        // 길이거나 문서거나 열쇠거나 문이면 bfs
                        if (!map[i][j].equals("*")) {
                            bfs(i, j);
                        }
                    }
                }

                // 열쇠를 하나도 못 주었으면서 문을 새로 안열었으면 끝내기
                if (newKeyCnt == 0 && openDoorCnt == 0) {
                    break;
                }
            }

            System.out.println(answer);

        }
    }

    // 열쇠나 문서를 만나면 수집하기
    // 문을 만나면 문을 열기
    public static void bfs(int y, int x) {

        boolean[][] visited = new boolean[h][w];
        Queue<Node> que = new LinkedList<>();

        char c = map[y][x].charAt(0);

        // 문서
        if (c == '$') {
            answer++;
            map[y][x] = ".";
        }

        // 열쇠
        if (c >= 97 && c <= 122) {
            newKeyCnt++;
            keys.add(String.valueOf(c));
            map[y][x] = ".";
        }

        // 문
        boolean flag = false;
        if (c >= 65 && c <= 90) {
            for (String key : keys) {
                if (key.charAt(0) - 32 == c) {
                    map[y][x] = ".";
                    openDoorCnt++;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return;
            }
        }

        que.add(new Node(y, x));
        visited[y][x] = true;


        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;

                if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                if (visited[ny][nx]) continue;
                if (map[ny][nx].equals("*")) continue;

                char next = map[ny][nx].charAt(0);
                // 열쇠(소문자)인 경우
                if (next >= 97 && next <= 122) {
                    newKeyCnt++;
                    keys.add(String.valueOf(next));
                    map[ny][nx] = ".";
                }

                // 문서인 경우
                if (next == '$') {
                    answer++;
                    map[ny][nx] = ".";
                }

                // 문인 경우
                if (next >= 65 && next <= 90) {
                    for (String key : keys) {
                        if (key.charAt(0) - 32 == next) {
                            map[ny][nx] = ".";
                            openDoorCnt++;
                            break;
                        }
                    }
                }

                // 다음으로 이동
                if (map[ny][nx].equals(".")) {
                    visited[ny][nx] = true;
                    que.add(new Node(ny, nx));
                }
            }
        }
    }

    public static class Node {
        int y, x;
        public Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
