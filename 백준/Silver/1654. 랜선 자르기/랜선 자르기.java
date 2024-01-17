
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: log(2^31 - 1)
public class Main {

    static int n;
    static int k;
    static int[] rope;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        rope = new int[k];
        long maxLen = 0;
        for (int i = 0; i < k; i++) {
            rope[i] = Integer.parseInt(br.readLine());
            maxLen = Math.max(maxLen, rope[i]);
        }
        
        maxLen++;
        long minLen = 0;
        long midLen = 0;
        
        while (minLen < maxLen) {
            midLen = (minLen + maxLen) / 2;
            int count = getCount(midLen); // midLen 길이로 자르면 생기는 밧줄 갯수

            // 길이 줄이기
            if (count < n) {
                maxLen = midLen;
            }
            // 길이 늘리기
            else {
                minLen = midLen + 1;
            }
        }
        // while 문이 끝난 후에는 minLen == maxLen
        System.out.println(minLen - 1);
    }

    private static int getCount(long midLen) {
        int count = 0;
        for (int i = 0; i < rope.length; i++) {
            count += (rope[i] / midLen);
        }
        return count;
    }
}