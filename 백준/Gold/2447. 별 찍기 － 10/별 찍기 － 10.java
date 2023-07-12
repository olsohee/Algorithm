import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static char[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        solve(N, 0, 0, false);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void solve(int size, int x, int y, boolean blank) {

        if(blank) {
            for(int i = y; i < y + size; i++) {
                for (int j = x; j < x + size; j++) {
                    map[i][j] = ' ';
                }
            }
            return;
        }

        if(size == 1) {
            map[y][x] = '*';
            return;
        }

        int count = 0;
        for(int i = x; i < x + size; i += size/3) {
            for(int j = y; j < y + size; j += size/3) {
                count++;
                if(count == 5) {
                    solve(size / 3, j, i, true);
                } else {
                    solve(size / 3, j, i, false);
                }
            }
        }
    }
}