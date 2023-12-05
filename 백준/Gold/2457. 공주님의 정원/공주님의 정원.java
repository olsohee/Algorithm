
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Flower> flowers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            flowers.add(new Flower(start, end));
        }

        // 시작일을 기준으로 오름차순 정렬 (만약 시작일이 같다면, 종료일이 늦은 순서로 정렬)
        Collections.sort(flowers);

        int point = 301;
        int start = 301;
        int idx = 0;
        int answer = 0;

        while(start <= 1130) {
            boolean flag = false;
            for(int i = idx; i < n; i++) {
                if(flowers.get(i).start > start) {
                    break;
                }

                if(flowers.get(i).end > point) {
                    point = flowers.get(i).end;
                    idx = i + 1;
                    flag = true;
                }
            }

            if(flag) {
                start = point;
                answer++;
            } else {
                // 아예 해당하는 꽃이 없는 경우
                break;
            }
        }

        if (point <= 1130) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }

    static class Flower implements Comparable<Flower> {
        int start;
        int end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.start == o.start) {
                return o.end - this.end;
            }
            return this.start - o.start;
        }
    }
}
