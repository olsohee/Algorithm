import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if (n <= k) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= k - 1; i++) {
            int base = 0;
            while (Math.pow(2, base) < n) {
                base++;
            }
            n -= Math.pow(2, base - 1);

            if (n == 0) {
                System.out.println(0);
                return;
            }
        }

        int base = 0;
        while (Math.pow(2, base) < n) {
            base++;
        }

        int answer = (int) Math.pow(2, base) - n;
        System.out.println(answer);
    }
}
