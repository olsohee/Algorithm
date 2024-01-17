
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도: 500,000 * log500,000
public class Main {

    static int n;
    static int m;
    static Map<Integer, Integer> cardsMap = new HashMap<>();
    static List<Integer> sortedCard;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int card = Integer.parseInt(st.nextToken());
            cardsMap.put(card, cardsMap.getOrDefault(card, 0) + 1);
        }

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int targetNumber = Integer.parseInt(st.nextToken());
            sb.append(cardsMap.getOrDefault(targetNumber, 0) + " ");
        }

//            boolean isContain = binarySearch(targetNumber);
//            if (isContain) {
//                sb.append(cardsMap.get(targetNumber) + " ");
//            } else {
//                sb.append(0 + " ");
//            }

        System.out.println(sb);
    }
}