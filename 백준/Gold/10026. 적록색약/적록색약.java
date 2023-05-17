import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] arr1;
    static char[][] arr2;
    static boolean[][] visited1;
    static boolean[][] visited2;
    static int count1;
    static int count2;
    static Queue<Pair> que;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr1 = new char[n][n];
        arr2 = new char[n][n];
        visited1 = new boolean[n][n];
        visited2 = new boolean[n][n];
        que = new LinkedList<>();

        //적록색약이 아닌 경우(arr1) 적록색약인 경우(arr2) 배열 초기화
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {

                arr1[i][j] = s.charAt(j);

                if (arr1[i][j] == 'G') {
                    arr2[i][j] = 'R';
                } else {
                    arr2[i][j] = arr1[i][j];
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                //적록색약이 아닌 경우
                if(!visited1[i][j]) { //방문하지 않은 경우
                    que.offer(new Pair(j, i));
                    visited1[i][j] = true; //방문 표시
                    count1++; //각 영역의 첫 스타트 때 count값 증가
                    bfs(arr1, visited1);
                }

                //적록색약인 경우
                if(!visited2[i][j]) {
                    que.offer(new Pair(j, i));
                    visited2[i][j] = true;
                    count2++;
                    bfs(arr2, visited2);
                }
            }
        }
        System.out.println(count1 + " " + count2);
    }

    public static void bfs(char[][] arr, boolean[][] visited) {
        while(!que.isEmpty()) {
            Pair p = que.poll();
            for(int i = 0; i < 4; i++) {
                int x = p.x + dx[i];
                int y = p.y + dy[i];

                if(x < 0 || x >= n || y < 0 || y >= n || visited[y][x] || arr[p.y][p.x] != arr[y][x]) {
                    continue;
                }

                que.offer(new Pair(x, y));
                visited[y][x] = true;
            }
        }
    }

    public static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
