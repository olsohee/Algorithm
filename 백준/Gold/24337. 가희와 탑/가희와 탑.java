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
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (a + b - 1 > n) {
            System.out.println(-1);
            return;
        }

        List<Integer> list = new LinkedList<>();

        int max = Math.max(a, b);

        for (int i = 1; i <= a - 1; i++) {
            list.add(i);
        }
        list.add(max);
        for (int i = 1; i <= b - 1; i++) {
            list.add(a, i);
        }

        while (list.size() < n) {
            if (a == 1) {
                list.add(1, 1);
            } else {
                list.add(0, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Integer integer : list) {
            sb.append(integer + " ");
//            System.out.print(integer + " ");
        }
        System.out.println(sb);
    }
}
