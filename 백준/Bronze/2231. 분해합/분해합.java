import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int numLen = str.length();
        
        int N = Integer.parseInt(str);
        
        for(int i = (N - numLen*9); i < N; i++) {
            int number = i;
            int sum = 0;
            
            while(number != 0) {
                sum += number % 10;
                number = number / 10;
            }
            
            if(sum + i == N) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}