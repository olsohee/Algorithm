import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[][] map;
    static List<Point> person;
    static List<Point> chicken;
    static int answer;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        person = new ArrayList<>();
        chicken = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) { //집인 경우
                    person.add(new Point(i, j));
                } else if (map[i][j] == 2) { //치킨집인 경우
                    chicken.add(new Point(i, j));
                }
            }
        }

        visited = new boolean[chicken.size()];
        answer = Integer.MAX_VALUE;

        dfs(0, 0);
        System.out.println(answer);
    }

    public static void dfs(int start, int count) {
        if(count == M) {
            int sum = 0; //sum: 모든 집들의 치킨거리의 합
            for(int i = 0; i < person.size(); i++) {
                int temp = Integer.MAX_VALUE; //temp: 각각의 집의 치킨거리
                for(int j = 0; j < chicken.size(); j++) {
                    if(visited[j]) { //M개의 치킨집만
                        int distance = Math.abs(person.get(i).x - chicken.get(j).x) +
                                Math.abs(person.get(i).y - chicken.get(j).y);
                        temp = Math.min(temp, distance);
                    }
                }
                sum += temp;
            }
            answer = Math.min(answer, sum);
            return;
        }

        for(int i = start; i < chicken.size(); i++) {
            visited[i] = true;
            dfs(i + 1, count + 1);
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