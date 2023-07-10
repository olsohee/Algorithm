import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int minus; // -1로만 채워진 종이 개수
    static int zero; // 0로만 채워진 종이 개수
    static int one; // 1로만 채워진 종이 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N);
        System.out.println(minus);
        System.out.println(zero);
        System.out.println(one);
    }

    static void solve(int x, int y, int size) {

        // 숫자가 섞이지 않은 경우
        if(check(x, y, size)) {
            int num = map[y][x];
            if(num == -1) {
                minus++;
            }
            if(num == 0) {
                zero++;
            }
            if(num == 1) {
                one++;
            }
            return;
        }

        // 숫자가 섞인 경우
        int newSize = size/3;
        for(int i = y; i < y + size; i += newSize) {
            for(int j = x; j < x + size; j+= newSize) {
                solve(j, i, newSize);
            }
        }
    }

    static boolean check(int x, int y, int size) {
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