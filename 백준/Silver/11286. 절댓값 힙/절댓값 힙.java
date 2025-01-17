import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Queue<Num> que = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                if (que.isEmpty()) {
                    sb.append(0).append('\n');
                } else {
                    sb.append(que.poll().origin).append('\n');
                }
            } else {
                que.add(new Num(num, Math.abs(num)));
            }
        }

        System.out.println(sb);
    }

    private static class Num implements Comparable<Num> {
        int origin;
        int absolute;

        public Num(int origin, int absolute) {
            this.origin = origin;
            this.absolute = absolute;
        }

        @Override
        public int compareTo(Num o) {
            if (this.absolute == o.absolute) {
                return this.origin - o.origin;
            }
            return this.absolute - o.absolute;
        }
    }
}
