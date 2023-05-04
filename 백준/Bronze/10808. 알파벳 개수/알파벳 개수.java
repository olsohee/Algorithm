import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] alArr = new int[26];
        int length = str.length();
        int[] arr = new int[length];

        for(int i = 0; i < length; i++) {
            arr[i] = str.charAt(i);
        }

        for(int i = 0; i < length; i++) {
            alArr[arr[i] - 97]++;
        }

        for(int i = 0; i < alArr.length; i++) {
            System.out.print(alArr[i] + " ");
        }
    }
}
