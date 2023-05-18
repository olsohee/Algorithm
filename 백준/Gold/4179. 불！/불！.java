
import java.io.*;
import java.util.*;

public class Main {

    static int r;
    static int c;
    static char[][] map;
    static Queue<Pos> jihoonQue;
    static Queue<Pos> fireQue;
    static boolean[][] jihoonVisited;
    static boolean[][] fireVisited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        jihoonVisited = new boolean[r][c];
        fireVisited = new boolean[r][c];
        jihoonQue = new LinkedList<>();
        fireQue = new LinkedList<>();

        //map 배열 초기화
        for(int i = 0; i < r; i++) {
            String s = br.readLine();
            for(int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'J') {
                    jihoonQue.offer(new Pos(j, i));
                    jihoonVisited[i][j] = true;
                    map[i][j] = '.';
                } else if(map[i][j] == 'F') {
                    fireQue.offer(new Pos(j, i));
                    fireVisited[i][j] = true;
                }
            }
        }

        bfs();
    }

    public static void bfs() {
        int time = 0;

        while(!jihoonQue.isEmpty()) {
            int jihoonLen = jihoonQue.size();
            int fireLen = fireQue.size();
            
            for(int j = 0; j < fireLen; j++) {
                Pos fireP = fireQue.poll();
                for(int i = 0; i < 4; i++) {
                    int x = fireP.x + dx[i];
                    int y = fireP.y + dy[i];

                    if(x < 0 || x >= c || y < 0 || y >= r || map[y][x] == '#' || fireVisited[y][x]) {
                        continue;
                    }

                    fireVisited[y][x] = true;
                    map[y][x] = 'F';
                    fireQue.offer(new Pos(x, y));
                }
            }
            
            for(int j = 0; j < jihoonLen; j++) {
                Pos jihoonP = jihoonQue.poll();
                for(int i = 0; i < 4; i++) {
                    int x = jihoonP.x + dx[i];
                    int y = jihoonP.y + dy[i];

                    if(x < 0 || x >= c || y < 0 || y >= r){
                        System.out.println(++time);
                        return;
                    }

                    if(map[y][x] != '.' || jihoonVisited[y][x]) {
                        continue;
                    }

                    jihoonVisited[y][x] = true;
                    jihoonQue.offer(new Pos(x, y));
                }
            }
            
            time++;
        }
        System.out.println("IMPOSSIBLE");
    }

    public static class Pos {
        int x, y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
