import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] times;
    static int[] endNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        times = new int[N][2];
        endNum = new int[N];
        boolean[] visited = new boolean[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i][0] = Integer.parseInt(st.nextToken());
            times[i][1] = Integer.parseInt(st.nextToken());
        }

        // 시작 시간이 빠른 순서대로 정렬
        Arrays.sort(times, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int count = 0; // 강의실 갯수

        // 첫번째 수업 강의실 배정
        count++;
        endNum[0] = times[0][1];
        visited[0] = true;

        // 두번째 수업부터 강의실 배정
        for(int i = 1; i < N; i++) {
            for(int j = 0; j < count; j++) {
                if(times[i][0] >= endNum[j]) { // 시간이 겹치지 않는 경우
                    endNum[j] = times[i][1]; // 종료 시간 변경
                    visited[i] = true;
                    break;
                }
            }
            // 다 비교해봤는데 시간이 겹치지 않는 경우가 없는 경우
            if(!visited[i]) {
                endNum[count] = times[i][1];
                count++;
            }
        }

        System.out.println(count);
    }
}