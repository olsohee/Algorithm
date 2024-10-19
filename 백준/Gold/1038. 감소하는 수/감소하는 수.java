
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        if (n < 10) {
            System.out.println(n);
        } else {
            for (int i = 0; i <= 9; i++) {
                func(i);
            }
            Collections.sort(list);
            if (n >= list.size()) {
                System.out.println(-1);
            } else {
                System.out.println(list.get(n));
            }
        }

        /*
        재귀
        - 시작: 0~9
        - 현재 숫자보다 작은 수가 이어서 나오게 재귀 진행

         */
    }

    private static void func(long num) {
        list.add(num);

        for (int i = 0; i < num % 10; i++) {
            func(num * 10 + i);
        }
    }
}