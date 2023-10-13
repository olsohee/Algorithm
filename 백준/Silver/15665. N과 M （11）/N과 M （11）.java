
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * 1. 아이디어
     * - 입력 받은 값 배열에 저장
     * - 방문 처리 X (중복 허용)
     *
     * 2. 시간 복잡도
     * - 중복 허용 O: N^N
     *
     * 3. 자료구조
     * - 입력 값 저장
     * - 출력 값 저장
     */

    static int N;
    static int M;
    static int[] map;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N];
        result = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순으로 정렬
        Arrays.sort(map);

        // 백트래킹
        func(0);
        System.out.println(sb);
    }

    public static void func(int count) {

        if(count == M) {
            for(int i = 0; i < M; i++) {
                sb.append(result[i] + " ");
            }
            sb.append('\n');
            return;
        }

        int preNum = -1;

        for(int i = 0; i < N; i++) {
            if(preNum != map[i]) {
                result[count] = map[i];
                preNum = map[i];
                func(count + 1);
            }
        }
    }
}