
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * 1. 아이디어
     * - 백트래킹
     *
     * 2. 시간 복잡도
     * - 중복 불가: N!
     *
     * 3. 자료구조
     * - 결과값 저장 int[]
     * - 방문 여부 체크 boolean[]
     */

    static int N;
    static int M;
    static StringTokenizer st;
    static BufferedReader br;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        // 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        visited = new boolean[N+1];

        // 백트래킹
        func(0);
    }

    public static void func(int k) {

        // M개 골랐으면 출력후 종료
        if(k == M) {
            for(int i = 0; i < M; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        // 재귀 호출
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                arr[k] = i;
                visited[i] = true;
                func(k + 1);

                // 재귀가 끝난 후에는 방문 처리 false
                visited[i] = false;
            }
        }
    }
}
