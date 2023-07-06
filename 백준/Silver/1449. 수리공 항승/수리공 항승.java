import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int L;
    static int result = 0;
    static int[] arr = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        // 물 새는 위치 입력받기
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[num] = 1;
        }

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == 1) {
                i = i + L - 1;
                result++;
            }
        }

        System.out.println(result);
    }
}