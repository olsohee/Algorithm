import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] inputArr = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            inputArr[i][0] = n1;
            inputArr[i][1] = n2;
        }

        // 배점이 높은 순으로 정렬
        Arrays.sort(inputArr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        int[] result = new int[10001];

        for(int i = 0; i < N; i++) {
            for(int j = inputArr[i][0]; j > 0; j--) {
                if(result[j] == 0) {
                    result[j] = inputArr[i][1];
                    break;
                }
            }
        }

        int sum = 0;
        for(int i = 0; i < result.length; i++) {
            sum += result[i];
        }

        System.out.println(sum);
    }
}