
import java.io.*;
import java.util.*;

public class Main {

    public static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        System.out.println(pow(a, b));
    }

    public static long pow(int a, int b) {

        if(b == 1) {
            return a % c;
        }

        long temp = pow(a, b / 2);

        if(b % 2 != 0) {
            return (temp * temp % c) * a % c;
        }

        return temp * temp % c;
    }
}
