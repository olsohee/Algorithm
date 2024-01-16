
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: O(logN * A원소 개수) = log500,000 * 500,000
public class Main {

    static int[] aArr;
    static int[] bArr;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        aArr = new int[Integer.parseInt(st.nextToken())];
        bArr = new int[Integer.parseInt(st.nextToken())];

        // a
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aArr.length; i++) {
            aArr[i] = Integer.parseInt(st.nextToken());
        }

        // b
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = Integer.parseInt(st.nextToken());
        }

        // bArr 정렬
        Arrays.sort(bArr);

        // B에 A의 수가 있는지 찾기
        for (int i = 0; i < aArr.length; i++) {
            int target = aArr[i];
            binarySearch(target);
        }

        System.out.println(answer.size());
        if (answer.size() > 0) {
            // 정렬
            Collections.sort(answer);
            for (Integer i : answer) {
                System.out.print(i + " ");
            }
        }
    }

    private static void binarySearch(int target) {
        int startIdx = 0;
        int endIdx = bArr.length - 1;
        int midIdx;

        while (startIdx <= endIdx) {
            midIdx = (startIdx + endIdx) / 2;

            // A의 숫자가 있는 경우
            if (bArr[midIdx] == target) {
                return;
            }

            if (bArr[midIdx] < target) {
                startIdx = midIdx + 1;
            } else {
                endIdx = midIdx - 1;
            }
        }

        // A의 숫자가 없는 경우
        answer.add(target);
    }
}