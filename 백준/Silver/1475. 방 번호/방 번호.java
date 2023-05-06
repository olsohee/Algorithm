
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int inputLen = input.length();

        int[] arr = new int[9]; //0~8의 갯수를 나타내는 배열 (6과 9는 동일하게 취급하여 arr[6]에 담을 예정)

        for(int i = 0; i < inputLen; i++) {
            if(input.charAt(i) - '0' == 9) {
                arr[6]++;
                continue;
            }
            arr[input.charAt(i) - '0']++;
        }

        int max = 0;
        for(int i = 0; i < arr.length; i++) {
            if(i == 6) {
                    max = Math.max((arr[6] + 1) / 2, max); 
                    continue;
            } 
            max = Math.max(arr[i], max);
        }

        System.out.println(max);
    }
}