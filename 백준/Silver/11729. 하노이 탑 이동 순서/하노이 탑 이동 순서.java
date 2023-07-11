import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int count;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        func(1, 3, N);
        System.out.println(count);
        System.out.println(sb);

    }

    static void func(int a, int b, int n) {

        count++;
        if(n == 1) {
            sb.append(a + " " + b + '\n');
            return;
        }

        func(a, 6-a-b, n-1);
        sb.append(a + " " + b + '\n');
        func(6-a-b, b, n-1);
    }
}