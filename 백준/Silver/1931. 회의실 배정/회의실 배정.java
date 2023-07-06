import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 종료 시간이 빠른 순서대로 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                if(o1[1] == o2[1]) {
                    return o1[0] - o2[0]; // 종료 시간이 같다면, 시작 시간이 빠른 순서대로 정렬
                }

                return o1[1] - o2[1]; // 종료 시간이 빠른 순서대로 정렬
            }
        });

        int result = 0;
        int end = 0;

        for(int i = 0; i < N; i++) {
            if(end <= arr[i][0]) {
                result++;
                end = arr[i][1];
            }
        }

        System.out.println(result);
    }
}