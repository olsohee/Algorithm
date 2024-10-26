import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] skills = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            skills[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(skills);

        long answer = 0;
        for (int i = 0; i < n; i++) {
            int goal = skills[i] * -1;
            // 투포인터
            int start = i + 1;
            int end = n - 1;
            while (start < end) {
                int sum = skills[start] + skills[end];
                if (sum == goal) {
                    if (skills[start] == skills[end]) {
                        answer += comb(end - start + 1);
                        break;
                    } else {
                        int sCnt = 1;
                        int eCnt = 1;
                        while (start + 1 < end && skills[start + 1] == skills[start]) {
                            sCnt++;
                            start++;
                        }
                        while (end - 1 > start && skills[end - 1] == skills[end]) {
                            eCnt++;
                            end--;
                        }
                        answer += sCnt * eCnt;
                        start++;
                    }
                } else if (sum > goal) {
                    end--;
                } else {
                    start++;
                }
            }
        }
        System.out.println(answer);
    }

    private static int comb(int n) {
        // n개 중 2개 고르는 경우의 수
        return n * (n - 1) / 2;
    }
}