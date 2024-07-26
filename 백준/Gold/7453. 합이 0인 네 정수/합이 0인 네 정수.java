import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[][] abcd = new int[n][4];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++) {
                abcd[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] ab = new int[n*n];
        int[] cd = new int[n*n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                ab[i*n+j]=abcd[i][0]+ abcd[j][1];
                cd[i*n+j]=(abcd[i][2]+ abcd[j][3]);
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        // 투포인터로 두 리스트를 탐색
        long answer = 0;

        int left = 0;
        int right = n * n - 1;

        while (left < n * n && right >= 0) {
            long num1 = ab[left];
            long num2 = cd[right];

            if (num1 + num2 > 0) {
                right--;
            } else if (num1 + num2 < 0) {
                left++;
            } else {
                int sameCnt1 = 0;
                while (left < n * n && ab[left] == num1) {
                    left++;
                    sameCnt1++;
                }

                int sameCnt2 = 0;
                while (right >= 0 && cd[right] == num2) {
                    right--;
                    sameCnt2++;
                }

                answer += (long)sameCnt1 * sameCnt2;
            }
        }

        System.out.println(answer);
    }
}