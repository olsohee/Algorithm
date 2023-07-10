import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            String input = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        solve(0, 0, N);

        System.out.println(sb.toString());
    }

    static void solve(int y, int x, int size) {

        // 모든 영역이 같은 값일 때
        if(check(y, x, size)) {;
            sb.append(map[y][x]);
            return;
        }

        sb.append("(");
        
        // 다른 값이 섞여 있을 때
        int newSize = size/2;
        for(int i = y; i < y + size; i += newSize) {
            for(int j = x; j < x + size; j += newSize) {
                solve(i, j, newSize);
            }
        }

        sb.append(")");
    }

    static boolean check(int y, int x, int size) {
        for(int i = y; i < y + size; i++) {
            for(int j = x; j < x + size; j++) {
                if(map[y][x] != map[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}