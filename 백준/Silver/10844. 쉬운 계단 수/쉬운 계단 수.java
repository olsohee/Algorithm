import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        long[][] arr = new long[n + 1][10];

        for (int i = 1; i < 10; i++) {
            arr[1][i] = 1;
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) {
                    arr[i][j] = arr[i - 1][1];
                } else if (j == 9) {
                    arr[i][j] = arr[i - 1][8];
                } else {
                    arr[i][j] = (arr[i - 1][j - 1] + arr[i - 1][j + 1]) % 1000000000;
                }
            }
        }

        long answer = 0;
        for (int i = 0; i < 10; i++) {
            answer += arr[n][i];
        }
        System.out.println(answer % 1000000000);
    }
}
