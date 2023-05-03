import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        for(int i = 0; i < 10; i++) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(str.nextToken()) - 1;
            int end = Integer.parseInt(str.nextToken()) - 1;

            for(int j = 0; j < (end - start + 1) / 2; j++) {
                int temp = arr[start + j];
                arr[start + j] = arr[end - j];
                arr[end - j] = temp;
            }
        }

        for(int i = 0; i < 20; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
