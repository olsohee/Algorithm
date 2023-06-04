import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, ans = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        //스티커 초기화
        for(int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[r][c];

            for(int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < c; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            findLocation(sticker);
        }
        System.out.println(ans);
    }

    private static void findLocation(int[][] sticker) {
        int r = sticker.length; //스티커 세로 길이
        int c = sticker[0].length; //스티커 제일 위쪽의 가로 길이

        for(int d = 0; d < 4; d++) { //회전하면서 4번 반복
            if(d != 0) {
                sticker = rotate(sticker, r, c);
                r = sticker.length;
                c = sticker[0].length;
            }
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(i + r > N || j + c > M) break;

                    if(putOn(i, j, r, c, sticker)) return;
                }
            }
        }
    }

    private static boolean putOn(int x, int y, int r, int c, int[][] sticker) {
        for (int i = x; i < x + r; i++) {
            for (int j = y; j < y + c; j++) {
                if (map[i][j] == 1 && sticker[i - x][j - y] == 1)
                    return false;
            }
        }

        for (int i = x; i < x + r; i++) {
            for (int j = y; j < y + c; j++) {
                if(sticker[i-x][j-y] == 1) {
                    map[i][j] = 1;
                    ans++;
                }
            }
        }

        return true;
    }

    private static int[][] rotate(int[][] sticker, int r, int c) {
        int[][] newSticker = new int[c][r];

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                newSticker[j][r - i - 1] = sticker[i][j];
            }
        }

        return newSticker;
    }
}