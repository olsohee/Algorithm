
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int m;
    static int[][] map;
    static List<Point> chickens = new ArrayList<>();
    static List<Point> homes = new ArrayList<>();
    static int answer = Integer.MAX_VALUE; // 치킨 거리의 최솟값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i + 1][j + 1] = Integer.parseInt(st.nextToken());
                if (map[i + 1][j + 1] == 2) {
                    chickens.add(new Point(i + 1, j + 1));
                }
                if (map[i + 1][j + 1] == 1) {
                    homes.add(new Point(i + 1, j + 1));
                }
            }
        }

        dfs(0, new ArrayList<>());

        System.out.println(answer);
    }

    private static void dfs(int depth, List<Point> selectedChickens) {
        // 다 돌았으면 끝
        if (depth == chickens.size()) {
            // m개 골랐으면 계산하기
            if (selectedChickens.size() == m) {
                answer = Math.min(answer, getChickenDist(selectedChickens));
            }
            return;
        }

        // 해당 인덱스 치킨 포함 O
        List<Point> copyList1 = new ArrayList<>(selectedChickens);
        copyList1.add(chickens.get(depth));
        dfs(depth + 1, copyList1);

        // 해당 인덱스 치킨 포함 X
        List<Point> copyList2 = new ArrayList<>(selectedChickens);
        dfs(depth + 1, copyList2);
    }

    private static int getChickenDist(List<Point> selectedChickens) {
        int totalDist = 0;
        for (int i = 0; i < homes.size(); i++) {
            Point home = homes.get(i);
            int minDist = Integer.MAX_VALUE;
            // 해당 집과 가장 가까운 치킨 집의 거리 찾기(minDist)
            for (int j = 0; j < selectedChickens.size(); j++) {
                Point chicken = selectedChickens.get(j);
                int dist = Math.abs(chicken.x - home.x) + Math.abs(chicken.y - home.y);
                minDist = Math.min(minDist, dist);
            }
            totalDist += minDist;
        }
        return totalDist;
    }

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
