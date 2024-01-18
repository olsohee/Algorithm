
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(N * logN)
public class Main {

    static int m; // 우주 개수
    static int n; // 행성 개수
    static List<int[]> arrList = new ArrayList<>();
    static List<int[]> sortedArrList = new ArrayList<>();
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            arrList.add(arr);
        }

        for (int i = 0; i < arrList.size(); i++) {
            int[] arr = arrList.get(i);
            int[] sortedArr = arr.clone();
            Arrays.sort(sortedArr);
            // 이분탐색
            for (int j = 0; j < arr.length; j++) {
                arr[j] = calculateIdx(sortedArr, arr[j]);
            }
            sortedArrList.add(arr);
        }

//        for (int i = 0; i < sortedArrList.size(); i++) {
//            int[] arr = sortedArrList.get(i);
//            // log
////            for (int i1 : arr) {
////                System.out.print(i1 + " ");
////            }
////            System.out.println();
//        }

        int answer = 0;
        for (int i = 0; i < sortedArrList.size(); i++) {
            for (int j = i + 1; j < sortedArrList.size(); j++) {
                if (Arrays.equals(sortedArrList.get(i), sortedArrList.get(j))) {
                    answer++;
                }
            }

        }
        System.out.println(answer);
    }

    // sortedArr 배열에서 target 숫자가 있는 인덱스를 찾기
    private static int calculateIdx(int[] sortedArr, int target) {
        int minIdx = 0;
        int maxIdx = sortedArr.length - 1;
        int midIdx = 0;
        while (minIdx <= maxIdx) {
            midIdx = (minIdx + maxIdx) / 2;
            if (sortedArr[midIdx] == target) {
                return midIdx;
            } else if (sortedArr[midIdx] < target) {
                minIdx = midIdx + 1;
            } else {
                maxIdx = midIdx - 1;
            }
        }
        return -1;
    }
}
