import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static List<Card> input = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input.add(new Card(i, Integer.parseInt(st.nextToken())));
        }
        m = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(0);
            return;
        }
        
        Collections.sort(input); // 숫자가 작은 순서대로 정렬

        // 1. 가장 싼 숫자를 살 수 있는 만큼 사서 "자릿수 늘리기"
        List<Card> cards = new ArrayList<>();
        if (input.get(0).idx == 0) {
            cards.add(input.get(1));
            m -= input.get(1).cost; // 남은 비용
            while (m >= input.get(0).cost) {
                cards.add(input.get(0));
                m -= input.get(0).cost;
            }
        } else {
            while (m >= input.get(0).cost) {
                cards.add(input.get(0));
                m -= input.get(0).cost;
            }
        }

        // 2. "각 자리 수 변경하기" (앞자리부터, 큰 숫자로)
        int[] answer = new int[cards.size()];
        // input 리스트를 크기가 큰 거 순으로 정렬
        Collections.sort(input, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o2.idx - o1.idx;
            }
        });

        for (int i = 0; i < cards.size(); i++) {
            m += cards.get(i).cost; // 돈 반납
            if (!replace(i, answer)) {
                answer[i] = cards.get(i).idx;
            }
        }
        for (int i : answer) {
            System.out.print(i);
        }
    }

    private static boolean replace(int idx, int[] answer) {
        for (int i = 0; i < input.size(); i++) {
            if (m < input.get(i).cost) {
                continue;
            }
            m -= input.get(i).cost; // 구매
            answer[idx] = input.get(i).idx;
            return true;
        }
        return false;
    }

    static class Card implements Comparable<Card> {
        int idx;
        int cost;

        public Card(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Card o) {
//            if (this.cost == o.cost) {
//                return this.idx - o.idx;
//            }
            return this.cost - o.cost;
        }
    }
}





