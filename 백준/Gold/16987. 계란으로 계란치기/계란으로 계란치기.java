
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

    static int N;
    static List<Egg> eggs = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs.add(new Egg(s, w));
        }


        dfs(0, 0);

        System.out.println(answer);
    }

    public static void dfs(int idx, int cnt) {

        if(idx == N) {

            answer = Math.max(answer, cnt);
            return;
        }

        Egg egg = eggs.get(idx);

        // 손에 든 계란이 깨진 경우
        if(egg.durability <= 0) {
            dfs(idx + 1, cnt);
            return;
        }
        // 나머지 계란이 다 깨진 경우
        else if(cnt == N - 1) {
            dfs(idx + 1, cnt);
            return;
        }

        // 다른 계란과 부딪히기
        int cpyCnt = cnt;
        for(int i = 0; i < N; i++) {
            Egg egg2 = eggs.get(i);
            if(i == idx) {
                continue;
            }

            if(egg2.durability <= 0) {
                continue;
            }

            // 계란 깨기
            egg2.durability -= egg.weight;
            egg.durability -= egg2.weight;

            if(egg.durability <= 0) {
                cnt++;
            }
            if(egg2.durability <= 0) {
                cnt++;
            }

            dfs(idx + 1, cnt);

            // 원상복구
            egg.durability += egg2.weight;
            egg2.durability += egg.weight;
            cnt = cpyCnt;
        }
    }

    static class Egg {
        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }
}