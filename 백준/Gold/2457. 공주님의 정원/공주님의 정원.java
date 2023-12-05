
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

        // 꽃이 피는 닐짜가 빠른 순으로 정렬 (만약 피는 날짜가 같으면 늦게 지는 순으로 정렬)
        Collections.sort(flowers);

        int maxEndDay = 301;
        int start = 301;
        int idx = 0;
        int flowerCount = 0;

        while (start <= 1130) { // 탈출 조건: 1130이어도 안되고 1130보다 커야 함
            boolean flag = false;
            for (int i = idx; i < n; i++) {
                if (flowers.get(i).start > start) {
                    break;
                }
                if (flowers.get(i).end > maxEndDay) {
                    maxEndDay = flowers.get(i).end;
                    idx = i + 1; // 다음 꽃을 찾을 때 굳이 i와 그 이전 인덱스의 꽃을 탐색할 필요가 없음
                    flag = true;
                }
            }
            if (flag) {
                start = maxEndDay;
                flowerCount++;
            } else {
                break; // 해당하는 꽃이 한 개도 없는 경우
            }
        }

        if (maxEndDay <= 1130) {
            System.out.println(0);
        } else {
            System.out.println(flowerCount);
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