
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] arr = new int[4][8];
    static int k;
    static int[] rotateArr = new int[4]; // 0: 회전 X, 1: 시계 방향 회전, -1: 반시계 방향 회전

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 4; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int point = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            // 회전할 톱니 판단하기
            Arrays.fill(rotateArr, 0);
            rotateArr[point] = direction;
            checkRotate(point);

            // 회전해야 하는 톱니 회전
            for (int j = 0; j < 4; j++) {
                if (rotateArr[j] == 1) {
                    rotateRight(arr[j]);
                }
                if (rotateArr[j] == -1) {
                    rotateLeft(arr[j]);
                }
            }
        }

        System.out.println(calculateScore());
    }

    private static void checkRotate(int point) {
        // 오른쪽에 있는 톱니 판단
        for (int i = point; i < 3; i++) {
            if (arr[i][2] != arr[i + 1][6]) {
                rotateArr[i + 1] = -1 * rotateArr[i];
            } else {
                break;
            }
        }

        // 왼쪽에 있는 톱니 판단
        for (int i = point; i > 0; i--) {
            if (arr[i][6] != arr[i - 1][2]) {
                rotateArr[i - 1] = -1 * rotateArr[i];
            } else {
                break;
            }
        }
    }

    // 시계 방향 회전
    private static void rotateRight(int[] target) {
        int temp = target[7];
        for (int i = 7; i >= 1; i--) {
            target[i] = target[i - 1];
        }
        target[0] = temp;
    }

    // 반시계 방향 회전
    private static void rotateLeft(int[] target) {
        int temp = target[0];
        for (int i = 0; i <= 6; i++) {
            target[i] = target[i + 1];
        }
        target[7] = temp;
    }

    private static int calculateScore() {
        int score = 0;
        if (arr[0][0] == 1) score += 1;
        if (arr[1][0] == 1) score += 2;
        if (arr[2][0] == 1) score += 4;
        if (arr[3][0] == 1) score += 8;
        return score;
    }
}
