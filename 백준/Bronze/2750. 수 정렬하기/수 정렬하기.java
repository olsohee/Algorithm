
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

        // 삽입 정렬
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    int temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
                else {
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(input[i]);
        }
    }
}