import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int inputLen = input.length();

        int[] arr = new int[9];

        for(int i = 0; i < inputLen; i++) {
            if(input.charAt(i) - '0' == 9) {
                arr[6]++;
            } else {
                arr[input.charAt(i) - '0']++;
            }
        }

        int max = 0;
        for(int i = 0; i < arr.length; i++) {
            if(i == 6) {
                    max = Math.max((arr[6] + 1) / 2, max);
            } else {
                max = Math.max(arr[i], max);
            }
        }

        System.out.println(max);
    }
}