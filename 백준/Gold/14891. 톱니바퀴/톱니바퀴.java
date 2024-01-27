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
            findIsLeftRotate(point, direction);
            findIsRightRotate(point, direction);

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

        calculateScore();
    }

    // 기준 톱니의 오른쪽 판단
    private static void findIsRightRotate(int point, int direction) {
        if (point > 2) {
            return;
        }
        if (arr[point][2] != arr[point + 1][6]) {
            rotateArr[point + 1] = direction * -1;
            findIsRightRotate(point + 1, direction * -1);
        }
    }

    // 기준 톱니의 왼쪽 판단
    private static void findIsLeftRotate(int point, int direction) {
        if (point < 1) {
            return;
        }

        if (arr[point][6] != arr[point - 1][2]) {
            rotateArr[point - 1] = direction * -1;
            findIsLeftRotate(point - 1, direction * -1);
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

    private static void calculateScore() {
        int score = 0;
        if (arr[0][0] == 1) {
            score += 1;
        }
        if (arr[1][0] == 1) {
            score += 2;
        }
        if (arr[2][0] == 1) {
            score += 4;
        }
        if (arr[3][0] == 1) {
            score += 8;
        }
        System.out.println(score);
    }
}