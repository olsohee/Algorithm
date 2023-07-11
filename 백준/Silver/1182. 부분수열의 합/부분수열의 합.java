import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int S;
    static int[] arr;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순으로 정렬
        Arrays.sort(arr);

        func(0, 0);

        // 크기가 양수인 부분수열만! 따라서 S가 0일 때는 모든 원소가 포함되지 않는 경우가 있으므로 1을 뺴주어야 함
        if(S == 0) {
            count--; 
        }
        System.out.println(count);
    }

    static void func(int index, int sum) {

        if(index == N) {
            if(sum == S) {
                count++;
            }
            return;
        }

        // 해당 인덱스 값을 포함하지 않는 경우
        func(index + 1, sum);

        // 해당 인덱스 값을 포함하는 경우
        func(index + 1, sum + arr[index]);
    }
}