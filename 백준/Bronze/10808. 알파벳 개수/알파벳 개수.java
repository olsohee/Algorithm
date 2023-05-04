import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();

        int[] freq = new int[26];
        int length = input.length();

        for(int i = 0; i < length; i++) {
            freq[input.charAt(i) - 97]++;
        }

        for(int i = 0; i < freq.length; i++) {
            sb.append(freq[i]).append(" ");
        }
        System.out.println(sb);
    }
}