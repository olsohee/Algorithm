import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도:
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int l;
    static int p;
    static int v;

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        int count = 0;

        while (true) {
            count++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            if (l == 0 && p == 0 && v == 0) {
                break;
            }
            int answer = (v / p * l) + Math.min(v % p, l);
            sb.append("Case " + count + ": " + answer).append('\n');
        }

        System.out.println(sb);
    }
}