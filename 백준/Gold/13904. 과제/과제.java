import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Pair[] inputArr = new Pair[N]; // 입력받은 값을 저장할 배열
        int maxDay = 0;

        for(int i = 0; i < inputArr.length; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Pair pair = new Pair(d, w);
            inputArr[i] = pair;
            maxDay = Math.max(maxDay, d);
        }

        Arrays.sort(inputArr, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.w - o1.w;
            }
        });

        int[] resultArr = new int[maxDay]; // 과제 순서를 저장할 배열
        int resultSum = 0;

        for(int i = 0; i < inputArr.length; i++) {
            for(int j = inputArr[i].d - 1; j >= 0; j--) {
                if(resultArr[j] == 0) {
                    resultArr[j] = inputArr[i].w;
                    resultSum += inputArr[i].w;
                    break;
                }
            }
        }
        System.out.println(resultSum);
    }

    public static class Pair {
        int d;
        int w;
        public Pair(int d, int w) {
            this.d = d;
            this.w = w;
        }
    }
}