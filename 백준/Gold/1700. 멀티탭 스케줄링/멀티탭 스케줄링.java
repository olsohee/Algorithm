import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 구멍 개수
        int k = Integer.parseInt(st.nextToken()); // 사용 횟수

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;

        int[] plug = new int[n];
        boolean[] used = new boolean[101];

        for (int i = 0; i < k; i++) {
            int num = arr[i];
            if (used[num]) continue; // 1. 이미 꽂혀있으면, 패스

            // 2. 꽂혀있지 않으면, n개의 구멍 중 하나에 꽂기
            // 2-1. 빈 공간에 꽂기
            boolean finish = false;
            for (int j = 0; j < n; j++) {
                if (plug[j] == 0) {
                    plug[j] = num;
                    used[num] = true;
                    finish = true;
                    break;
                }
            }
            // 2-2. 빈 공간이 없으면 다음에 사용하는 시기가 늦은 곳을 빼고 넣기
            if (!finish) {
                int maxNextIdx = 0;
                int willUseIdx = 0;

                for (int j = 0; j < n; j++) {
                    int usedNum = plug[j];
                    int nextIdx = k;
                    for (int m = i + 1; m < k; m++) {
                        if (arr[m] == usedNum){
                            nextIdx = m;
                            break;
                        }
                    }
                    if (nextIdx > maxNextIdx) {
                        maxNextIdx = nextIdx;
                        willUseIdx = j;
                    }
                }

                used[plug[willUseIdx]] = false;
                plug[willUseIdx] = num;
                used[num] = true;

                answer++;
            }
        }
        System.out.println(answer);
    }
}
