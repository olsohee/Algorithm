
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * 1. 아이디어
     * - 백트래킹
     * - 중복 허용
     * - 재귀 함수 호출 시, 반복 시작 값 전달하기
     *
     * 2. 시간 복잡도
     * - 중복 허용: N^N
     *
     * 3. 자료구조
     * - 결과를 저장할 int[]
     */

    static int N;
    static int M;
    static StringTokenizer st;
    static BufferedReader br;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];

        // 백트래킹
        func(0, 1);
    }

    public static void func(int m, int start) {

        // 종료 조건
        if(m == M) {
            for(int i = 0; i < m; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        // 재귀 호출
        for(int i = start; i <= N; i++) {
            arr[m] = i;
            func(m + 1, i);
        }
    }
}