import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] map = new int[8][8];
    static int[] dy = {0, 0, 1, -1, -1, -1, 1, 1};
    static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        Map<String, Integer> dirIdxMap = new HashMap<>();
        dirIdxMap.put("R", 0);
        dirIdxMap.put("L", 1);
        dirIdxMap.put("B", 2);
        dirIdxMap.put("T", 3);
        dirIdxMap.put("RT", 4);
        dirIdxMap.put("LT", 5);
        dirIdxMap.put("RB", 6);
        dirIdxMap.put("LB", 7);

        st = new StringTokenizer(br.readLine());

        char[] charArray = st.nextToken().toCharArray();
        int kingX = charArray[0] - 65;
        int kingY = 8 - (charArray[1] - '0');
        map[kingY][kingX] = 1; // 킹 위치 = 1로 표시

        charArray = st.nextToken().toCharArray();
        int stoneX = charArray[0] - 65;
        int stoneY = 8 - (charArray[1] - '0');
        map[stoneY][stoneX] = -1; // 돌 위치 = -1로 표시

        int n = Integer.parseInt(st.nextToken());

        outer: for (int i = 0; i < n; i++) {
            String command = br.readLine();
            Integer dir = dirIdxMap.get(command);
            int ny = kingY + dy[dir];
            int nx = kingX + dx[dir];

            // 범위 벗어나는 경우 -> 패스
            if (ny < 0 || ny >= 8 || nx < 0 || nx >= 8) {
                continue;
            }

            // 돌에 부딪히는 경우
            if (map[ny][nx] == -1) {
                int stoneNy = stoneY + dy[dir];
                int stoneNx = stoneX + dx[dir];
                // 돌이 범위 벗어나는 경우 -> 패스
                if (stoneNy < 0 || stoneNy >= 8 || stoneNx < 0 || stoneNx >= 8) {
                    continue outer;
                }

                // 돌 이동
                map[stoneY][stoneX] = 0;
                stoneY = stoneNy;
                stoneX = stoneNx;
                map[stoneY][stoneX] = -1;
            }

            map[kingY][kingX] = 0;
            kingY = ny;
            kingX = nx;
            map[kingY][kingX] = 1;
        }

        // 킹의 마지막 위치
        System.out.println((char) (kingX + 65) + ""+ (8 - kingY));

        // 돌의 마지막 위치
        System.out.println((char) (stoneX + 65) + "" + (8 - stoneY));
    }
}
