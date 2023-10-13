
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /**
     * 1. 아이디어
     * - 7명의 조합 -> 백트래킹
     * - 7명이 인접해있는지 -> bfs
     *
     * 2. 시간 복잡도
     * - 중복 허용 X: N!
     *
     * 3. 자료구조
     * - 입력 값 저장할 char[][] map
     * - 방문 체크 boolean[][] visited
     * - 7공주를 저장할 char[] result
     */

    static char[][] map = new char[5][5];
    static boolean[][] visited = new boolean[5][5];
    static int resultCount = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        for(int i = 0; i < 5; i++) {
            String s = br.readLine();
            for(int j = 0; j < 5; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        func(0, 0);
        System.out.println(resultCount);
    }

    public static void func(int count, int start) {

        // 종료 조건
        if(count == 7) {
            if(validate()) {
                resultCount++;
            }
            return;
        }

        // 백트래킹
        for(int i = start; i < 25; i++) {
            visited[i / 5][i % 5] = true;
            func(count + 1, i + 1);
            visited[i / 5][i % 5] = false;
        }
//
//        for(int i = 0; i < 5; i++) {
//            for(int j = 0; j < 5; j++) {
//                if(!visited[i][j]) {
//                    visited[i][j] = true;
//                    func(count + 1);
//                    visited[i][j] = false;
//                }
//            }
//        }
    }

    public static boolean validate() {

        boolean[][] visitedCpy = new boolean[5][5];
        for(int i = 0; i < 5; i++) {
            visitedCpy[i] = visited[i].clone();
        }

        Queue<Point> que = new LinkedList<>();
        int x =0;
        int y = 0;
        int sCount = 0;
        int count = 0;

        // bfs
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(visitedCpy[i][j]) {
                    x = j;
                    y = i;
                }
            }
        }

        que.offer(new Point(x, y));
        visitedCpy[y][x] = false;

        while(!que.isEmpty()) {
            Point p = que.poll();
            count++;
            if(map[p.y][p.x] == 'S') {
                sCount++;
            }

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || !visitedCpy[ny][nx]) {
                    continue;
                }

                que.offer(new Point(nx, ny));
                visitedCpy[ny][nx] = false;
            }
        }

        if(count == 7 && sCount >= 4) {
            return true;
        } else {
            return false;
        }
    }

    static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}