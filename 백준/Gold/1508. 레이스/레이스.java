
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
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()); // k개의 위치에 m명 위치시키기

        st = new StringTokenizer(br.readLine());
        int[] locations = new int[k];
        for (int i = 0; i < k; i++) {
            locations[i] = Integer.parseInt(st.nextToken());
        }

        // 이분탐색 (심판 간 최대 거리 찾기)
        int start = 1;
        int end = n;

        while (start <= end) {
            int mid = (start + end) / 2;

            // 심판 간 최대 거리가 mid일 때, 위치시키는 심판 명수
            int cnt = 1; // locations[0]에 무조건 위치시킴
            int preLocation = locations[0];
            for (int i = 1; i < locations.length; i++) {
                if (locations[i] - preLocation >= mid) {
                    cnt++;
                    preLocation = locations[i];
                }
            }

            if (cnt >= m) {
                start = mid + 1; // m명보다 많이 위치시킬 수 있으면 -> 간격 더 넒게 조정
            } else {
                end = mid - 1;
            }
        }

        // end = 심판 간 최대 거리
        // 사전순으로 늦은 것을 출력 = 맨 앞부터 심판 위치시키기
        int[] answer = new int[locations.length];
        answer[0] = 1;
        int locateCnt = 1;
        int preLocation = locations[0];
        for (int i = 1; i < locations.length; i++) {
            if (locateCnt == m) break;
            if (locations[i] - preLocation >= end) {
                preLocation = locations[i];
                answer[i] = 1;
                locateCnt++;
            }
        }

        for (int i : answer) {
            System.out.print(i);
        }
    }
}
