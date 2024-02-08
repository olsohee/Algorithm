import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 =
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int k;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if (k > n) {
            System.out.println(0);
            return;
        }
        Arrays.sort(arr);

        // 공백 계산
        List<Empty> list = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            list.add(new Empty(i, i + 1, arr[i + 1] - arr[i]));
        }

        Collections.sort(list); // 공백 큰 순서부터 작은 순서로 내림차순 정렬

        // 가장 큰 k - 1개의 공백 걸러내기
        int[] endArr = new int[k - 1];
        for (int i = 0; i < k - 1; i++) {
            endArr[i] = list.get(i).startIdx;
        }
        Arrays.sort(endArr);

        // 전체 센서 돌면서 집중국 개수 계산하기
        int answer = 0;
        int preIdx = 0;
        for (int i = 0; i < k - 1; i++) {
            int endIdx = endArr[i];
            answer += arr[endIdx] - arr[preIdx];
            preIdx = endIdx + 1;
        }
        answer += arr[n - 1] - arr[preIdx];

        System.out.println(answer);
    }

    static class Empty implements Comparable<Empty> {
        int startIdx;
        int endIdx;
        int empty;

        public Empty(int startIdx, int endIdx, int empty) {
            this.startIdx = startIdx;
            this.endIdx = endIdx;
            this.empty = empty;
        }

        @Override
        public int compareTo(Empty o) {
            return o.empty - empty;
        }
    }
}
