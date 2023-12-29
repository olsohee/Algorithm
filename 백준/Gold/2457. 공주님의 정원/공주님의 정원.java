
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

// 시간복잡도: O(N) = 100,000
public class Main {

    static int n;
    static List<Flower> flowers = new ArrayList<>();
    static int answer = 0; // 꽃의 최소 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            flowers.add(new Flower(start, end));
        }

        Collections.sort(flowers);

        int preEnd = 301;
        int tempEnd = 0;
        int tempStart = 0;
        boolean flag = false;

        for (int i = 0; i < n; i++) {
            Flower now = flowers.get(i);
            if (now.start == tempStart) {
                continue;
            }
            if (now.start <= preEnd) {
                if (now.end > tempEnd) {
                    tempEnd = now.end;
                    tempStart = now.start;
                    flag = true;
                }
            } else {
                if (!flag) {
                    break;
                } else {
                    preEnd = tempEnd; // i - 1번째 꽃 선택
                    answer++; // 꽃을 선택했으므로 카운트
                    flag = false;
                    i--; // i - 1번째 꽃을 선택한 후에 i번째부터 탐색을 다시 해야 하므로
                }
            }

            if (tempEnd > 1130) {
                System.out.println(++answer);
                return;
            }
        }

        System.out.println(0);
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