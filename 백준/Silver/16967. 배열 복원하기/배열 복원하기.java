import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int[][] arr = new int[H + X][W + Y];
        for (int i = 0; i < H + X; i++) {
            st =  new StringTokenizer(br.readLine());
            for (int j = 0; j < W + Y; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = X; i < H + X; i++) {
            for (int j = Y; j < W + Y; j++) {
                arr[i][j] = arr[i][j] - arr[i - X][j - Y];
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
