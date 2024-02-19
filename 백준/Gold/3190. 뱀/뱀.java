import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int l;
    static int[][] map;
    static int time;
    static Map<Integer, String> turnMap = new HashMap<>();
    static List<Position> positions = new ArrayList<>();
    static int[] dx = {1, 0, -1, 0}; // dir: 0(오), 1(아래), 2(왼), 3(위)
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            turnMap.put(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        positions.add(new Position(1, 1));
        time = 0;
        int dir = 0;
        while (true) {
            int nx = positions.get(0).x + dx[dir];
            int ny = positions.get(0).y + dy[dir];

            // 이동 가능한지 검증
            if (isFinish(ny, nx)) {
                break;
            }

            // 이동
            if (map[ny][nx] == 1) {
                // 사과가 있는 경우
                positions.add(0, new Position(ny, nx)); // 앞머리 추가
                map[ny][nx] = 0;
            } else {
                // 사과가 없는 경우
                positions.remove(positions.size() - 1); // 꼬리 제거
                positions.add(0, new Position(ny, nx)); // 앞머리 추가
            }

            // 시간 추가
            time++;

            // 방향 회전 O/X
            if (turnMap.containsKey(time)) {
                if (turnMap.get(time).equals("L")) {
                    dir -= 1;
                    if (dir == -1) dir = 3;
                } else {
                    dir += 1;
                    if (dir == 4) dir = 0;
                }
            }
        }

        System.out.println(time + 1);
    }

    private static boolean isFinish(int ny, int nx) {
        // 그래프 범위 확인
        if (nx <= 0 || nx > n || ny <= 0 || ny > n) {
            return true;
        }

        for (int i = 0; i < positions.size(); i++) {
            Position position = positions.get(i);
            if (position.y == ny && position.x == nx) {
                return true;
            }
        }

        return false;
    }

    static class Position {
        int y, x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}