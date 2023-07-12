import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int whiteCnt;
    static int blueCnt;
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, N);
        System.out.println(whiteCnt);
        System.out.println(blueCnt);
    }

    public static void solve(int x, int y, int size) {

        for(int i = y; i < y + size; i++) {
            for(int j = x; j < x + size; j++) {
                if(paper[y][x] != paper[i][j]) {
                    // 1사분면
                    solve(x, y, size/2);

                    // 2사분면
                    solve(x + size/2, y, size/2);

                    // 3사분면
                    solve(x, y + size/2, size/2);

                    // 4사분면
                    solve(x + size/2, y + size/2, size/2);
                    return;
                }
            }
        }

        if(paper[y][x] == 1) {
            blueCnt++;
        }

        if(paper[y][x] == 0) {
            whiteCnt++;
        }
    }
}