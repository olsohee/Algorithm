import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int R;
    static int C;
    static int num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        solve(0, 0, (int)Math.pow(2, N));
    }

    public static void solve(int r, int c, int size) {

        if(size == 1) {
            System.out.println(num);
            return;
        }

        int newSize = size / 2;

        if(R < r + newSize && C < c + newSize) {
            solve(r, c, newSize);
        }

        if(R < r + newSize && C >= c + newSize) {
            num += newSize * newSize;
            solve(r, c + newSize, newSize);
        }

        if(R >= r + newSize && C < c + newSize) {
            num += newSize * newSize * 2;
            solve(r + newSize, c, newSize);
        }

        if(R >= r + newSize && C >= c + newSize) {
            num += newSize * newSize * 3;
            solve(r + newSize, c + newSize, newSize);
        }
    }
}