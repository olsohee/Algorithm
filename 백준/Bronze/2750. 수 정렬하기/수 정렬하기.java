
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 시간 복잡도: O(N^2)
public class Main {

    static int n;
    static int[] input;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        // 선택 정렬
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i; j < n; j++) {
                if (input[minIndex] > input[j]) {
                    minIndex = j;
                }
            }
            // i번째 자리의 수와 minIndex의 수를 바꾸기
            int temp = input[i];
            input[i] = input[minIndex];
            input[minIndex] = temp;
        }

        for (int i = 0; i < n; i++) {
            System.out.println(input[i]);
        }
    }
}