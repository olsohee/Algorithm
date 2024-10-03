
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Node> snake = new LinkedList<>();
    static List<Time> timeList = new ArrayList<>();
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};
    static int direction = 0; // 0:오, 1:아래, 2:왼, 3:위
    static int gameTime = 0;
    static int timeIdx = 0;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n + 1][n + 1];
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = 1; // 사과가 있는 위치 = 1
        }

        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            timeList.add(new Time(time, direction));
        }

        Collections.sort(timeList, (o1, o2) -> o1.time - o2.time);

        // 뱀 이동 시작
        snake.add(new Node(1, 1));

        while (true) {

            int ny = snake.get(0).y + dy[direction];
            int nx = snake.get(0).x + dx[direction];

            // 벽에 닿거나, 자기 자신에 닿으면 -> 끝내기
            if (ny <= 0 || nx <= 0 || ny > n || nx > n || isTouchSnake(ny, nx)) {
                gameTime++;
                break;
            }

            // 사괴이면 머리 넣기
            if (map[ny][nx] == 1) {
                snake.add(0, new Node(ny, nx));
                map[ny][nx] = 0;
            }

            // 빈 공간이면 머리 넣기 + 꼬리 빼기
            else {
                snake.add(0, new Node(ny, nx));
                snake.remove(snake.size() - 1);
            }

            gameTime++;
            if (timeIdx < timeList.size()) {
                if (timeList.get(timeIdx).time == gameTime) {
                    turn();
                }
            }
        }

        System.out.println(gameTime);
    }

    private static boolean isTouchSnake(int ny, int nx) {

        for (Node snakeLocation : snake) {
            if (snakeLocation.y == ny && snakeLocation.x == nx) {
                return true;
            }
        }
        return false;
    }

    private static void turn() {
        // 왼쪽으로 회전
        if (timeList.get(timeIdx).direction.equals("L")) {
            direction--;
            if (direction == -1) direction = 3;
        }

        // 오른쪽으로 회전
        if (timeList.get(timeIdx).direction.equals("D")) {
            direction++;
            if (direction == 4) direction = 0;
        }

        timeIdx++;
    }

    private static class Node {

        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static class Time {

        int time;
        String direction;

        public Time(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }
}