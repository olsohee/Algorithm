
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;
    static List<Number> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(new Number(br.readLine()));
        }

        Collections.sort(list);
        for (Number number : list) {
            System.out.println(number.number);
        }
    }

    static class Number implements Comparable<Number> {
        String number;

        public Number(String number) {
            this.number = number;
        }

        public int sumNum() {
            String[] arr = this.number.split("");
            int sum = 0;
            for (String s : arr) {
                try {
                    sum += Integer.parseInt(s);
                } catch (NumberFormatException e) {
                }
            }
            return sum;
        }

        @Override
        public int compareTo(Number o) {
            if (this.number.length() == o.number.length()) {
                if (this.sumNum() == o.sumNum()) {

                    return this.number.compareTo(o.number);
                }
                return this.sumNum() - o.sumNum();
            }

            return this.number.length() - o.number.length();
        }
    }
}
