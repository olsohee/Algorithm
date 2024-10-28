import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[][] visited = new boolean[1001][1001];
    static int sum;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        sum = a + b + c;

        Queue<Stone> que = new LinkedList<>();
        que.add(new Stone(a, b, c));
        visited[a][b] = true;
        int answer = 0;
        while (!que.isEmpty()) {
            Stone stone = que.poll();
            if (stone.a == stone.b && stone.b == stone.c) {
                answer = 1;
                break;
            }

            // a, b 재조정
            if (stone.a != stone.b) {
                int newA = stone.a > stone.b ? stone.a - stone.b : stone.a * 2;
                int newB = stone.a > stone.b ? stone.b * 2 : stone.b - stone.a;
                if (newA <= 1000 && newB <= 1000 && !visited[newA][newB]) {
                    visited[newA][newB] = true;
                    que.add(new Stone(newA, newB, stone.c));
                }
            }

            // a, c 재조정
            if (stone.a != stone.c) {
                int newA = stone.a > stone.c ? stone.a - stone.c : stone.a * 2;
                int newC = stone.a > stone.c ? stone.c * 2 : stone.c - stone.a;
                if (newA <= 1000 && !visited[newA][stone.b]) {
                    visited[newA][stone.b] = true;
                    que.add(new Stone(newA, stone.b,newC));
                }
            }

            // b, c 재조정
            if (stone.b != stone.b) {
                int newB = stone.b > stone.c ? stone.b - stone.c : stone.b * 2;
                int newC = stone.b > stone.c ? stone.c * 2 : stone.c - stone.b;
                if (newB <= 1000 && !visited[stone.a][newB]) {
                    visited[stone.a][newB] = true;
                    que.add(new Stone(stone.a, newB, newC));
                }
            }
        }

        System.out.println(answer);
    }

    static class Stone {
        int a; // 첫번째 그룹의 개수
        int b; // 두번째 그룹의 개수
        int c; // 세번째 그룹의 개수

        public Stone(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}