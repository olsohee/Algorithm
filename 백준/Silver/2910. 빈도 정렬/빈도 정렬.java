import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int c;
    static List<Input> answer = new ArrayList<>();
    static Map<Integer, int[]> map = new HashMap<>();

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int key = Integer.parseInt(st.nextToken());
            if (map.containsKey(key)) {
                int[] arr = map.get(key); // arr[0]: 갯수, arr[1]: 입력 순서
                arr[0]++;
                map.put(key, arr);
            } else {
                int[] arr = new int[2];
                arr[0] = 1;
                arr[1] = i;
                map.put(key, arr);
            }
        }

        for (Integer key : map.keySet()) {
            int[] arr = map.get(key);
            answer.add(new Input(key, arr[0], arr[1]));
        }

        Collections.sort(answer);
        for (Input input : answer) {
            for (int i = 0; i < input.count; i++) {
                System.out.print(input.num + " ");
            }
        }
    }

    static class Input implements Comparable<Input> {
        int num;
        int count;
        int inputOrder;

        public Input(int num, int count, int inputOrder) {
            this.num = num;
            this.count = count;
            this.inputOrder = inputOrder;
        }

        @Override
        public int compareTo(Input o) {
            if (o.count == count) {
                return inputOrder - o.inputOrder;
            }
            return o.count - count;
        }
    }
}
