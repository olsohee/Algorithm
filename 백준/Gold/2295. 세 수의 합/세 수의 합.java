import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 = O(N^2 * logN)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] arr;
    static List<Integer> list = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        // 이중 반복문으로 a + b의 집합 만들기
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                list.add(arr[i] + arr[j]);
            }
        }
        Collections.sort(list);

        // 이분 탐색으로 x - c를 찾고, a + b 집합에 있는지 확인하기
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
//                Collections.binarySearch() 메소드를 사용하는 경우
//                if (Collections.binarySearch(list, arr[j] - arr[i]) >= 0) {
//                    answer = Math.max(answer, arr[j]);
//                }
                binarySearch(arr[j], arr[i]);
            }
        }

        System.out.println(answer);
    }

    private static void binarySearch(int max, int min) {
        int diff = max - min;
        int start = 0;
        int end = list.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            // 있는 경우
            if (list.get(mid) == diff) {
                answer = Math.max(answer, max);
                return;
            }
            if (list.get(mid) > diff) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
    }
}