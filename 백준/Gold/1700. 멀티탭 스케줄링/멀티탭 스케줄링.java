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
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[k];
        boolean[] used = new boolean[101];
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        int answer = 0;

        for (int i = 0; i < k; i++) {
            int plug = arr[i];
            
            // 이미 꽂혀있는 플러그이면 pass
            if (used[plug]) {
                continue;
            }

            // 사용중인 구멍이 n개 미만이면 꽂기
            if (cnt < n) {
                used[plug] = true;
                cnt++;
            }

            // 사용중인 구멍이 n개 이상이면 최대한 늦게 사용되는 플러그를 빼고 꽂기
            else {
                // 최대한 늦게 사용되는 플러그 찾기
                Map<Integer, Integer> map = new HashMap<>();
                for (int j = 1; j <= 100; j++) {
                    map.put(j, Integer.MAX_VALUE);
                }
                for (int j = i + 1; j < k; j++) {
                    int newPlug = arr[j];
                    if (map.get(newPlug) > j) {
                        map.put(newPlug, j);
                    }
                }

                int maxTime = 0;
                int out = 0;

                for (int key : map.keySet()) {
                    if (used[key] && map.get(key) >= maxTime) {
                        out = key;
                        maxTime = map.get(key);
                    }
                }

                // out이 가장 늦게 사용되는 플러그이므로 out을 빼기
                used[out] = false;
                used[plug] = true;
                answer++;
            }
        }

        System.out.println(answer);
    }
}
