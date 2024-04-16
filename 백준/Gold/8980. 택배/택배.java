import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        List<List<Package>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            list.get(from).add(new Package(to, cnt));
        }

        // 1~n까지 접근하며 택배 싣고 보내기
        int[] truck = new int[n + 1];
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            // 택배 내리기
            answer += truck[i];
            sum -= truck[i];
            truck[i] = 0;

            // 택배 싣기
            for (Package p : list.get(i)) {
                truck[p.to] += p.cnt;
                sum += p.cnt;
            }

            if (sum > c) {
                int reduce = 0; // sum - c만큼 빼야 함
                for (int j = n; j >= 0; j--) {
                    if (truck[j] == 0) continue;
                    // 더 빼야 할 양: sum - c - reduce
                    if (truck[j] < sum - c - reduce) {
                        reduce += truck[j];
                        truck[j] = 0;
                    } else {
                        truck[j] -= sum - c - reduce;
                        break;
                    }
                }
                sum = c;
            }
        }

        System.out.println(answer);
    }

    public static class Package {
        int to, cnt;
        public Package (int to, int cnt) {
            this.to = to;
            this. cnt = cnt;
        }
    }
}
