
import java.io.*;
import java.util.*;
import java.util.List;

/*
    재귀
    집, 치킨을 각 리스트에 저장
    치킨 리스트들을 M개 고르기 (방문처리로 표시)
    M개 다 고른 후에는, 방문처리 True인 치킨집들과 집들을 통해 도시의 치킨거리 구한 후 최소값 출력
 */


public class Main {

    static int N;
    static int M;
    static List<Point> homeList = new ArrayList<>();
    static List<Point> chickenList = new ArrayList<>();
    static boolean[] visited;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 1) {
                    homeList.add(new Point(j, i));
                } else if(num == 2) {
                    chickenList.add(new Point(j, i));
                }
            }
        }

        visited = new boolean[chickenList.size()];

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        func(0, 0);

        System.out.println(result);
    }

    public static void func(int start, int cnt) {

        if(cnt == M) {
            int sum = 0;
            // 집들과 치킨집들 간의 거리 계산
            for(int i = 0; i < homeList.size(); i++) {

                int min = Integer.MAX_VALUE;
                Point homePoint = homeList.get(i);
                for(int j = 0; j < visited.length; j++) {
                    if(visited[j]) {
                        Point chickenPoint = chickenList.get(j);
                        min = Math.min(min, Math.abs(chickenPoint.x - homePoint.x) + Math.abs(chickenPoint.y - homePoint.y));
                    }
                }
                sum += min;
            }
            result = Math.min(result, sum);
            return;
        }

        // 치킨집 고르기 (총 M개)
//        for(int i = start; i < chickenList.size(); i++) {
//            if(!visited[i]) {
//                visited[i] = true;
//                func(start + 1, cnt + 1);
//                visited[i] = false;
//            }
//        }
        for(int i = start; i < chickenList.size(); i++) {
            visited[i] = true;
            func(i + 1, cnt + 1);
            visited[i] = false;
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