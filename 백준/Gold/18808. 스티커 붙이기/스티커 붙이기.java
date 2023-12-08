
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n; // 세로
    static int m; // 가로
    static int k; // 스티커 개수
    static int[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < k; i++) {
            // 스티커 입력받기
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[r][c];
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < c; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            // 스티커 붙이기
            putSticker(sticker);
        }

        // 스티커가 붙은 칸 수 계산
        getStickerArea();
        System.out.println(answer);
    }

    private static void putSticker(int[][] sticker) {

        for (int i = 0; i < 4; i++) {
            // 지금 방향에서 붙일 수 있는지 확인
            int x = sticker[0].length; // 가로
            int y = sticker.length; // 세로

            // map 범위 내에 있다면 붙이기 시도
            for (int j = 0; j <= (n - y); j++) {
                for (int k = 0; k <= (m - x); k++) {
                    // map 범위를 벗어나지 않는지 확인
                    if (j + y > n || k + x > m) {
                        continue;
                    }
                    // 붙이기
                    if (canPut(sticker, j, k)) {
                        return;
                    }
                }
            }
            // 90도로 스티커 회전
            sticker = rotation(sticker);
        }
    }

    private static boolean canPut(int[][] sticker, int startY, int startX) {
        int x = sticker[0].length; // 가로
        int y = sticker.length; // 세로

        // 붙일 수 있는지 확인 (스티커가 1인데 map도 1이면 못붙임)
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (sticker[i][j] == 1 && map[i + startY][j + startX] == 1) {
                    return false;
                }
            }
        }

        // 붙이기
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (sticker[i][j] == 1) {
                    map[i + startY][j + startX] = 1;
                }
            }
        }
        return true;
    }


    private static int[][] rotation(int[][] sticker) {
        int newX = sticker.length;
        int newY = sticker[0].length;
        int[][] newSticker = new int[newY][newX];

        for (int i = 0; i < newX; i++)
            for (int j = 0; j < newY; j++)
                newSticker[j][newX - i - 1] = sticker[i][j];

        return newSticker;
    }

    private static void getStickerArea() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    answer++;
                }
            }
        }
    }
}
