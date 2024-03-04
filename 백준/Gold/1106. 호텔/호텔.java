import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int c;
    static int n;
    static List<City> list = new ArrayList<>();
    static int[] dp = new int[1101];
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            City city = new City(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            list.add(city);
        }

        // 재귀로 조합 찾기 + dp로 중복되는 계산 제거
        Arrays.fill(dp, Integer.MAX_VALUE);
        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int money, int customerCnt) {

        if (dp[customerCnt] > money) {
            dp[customerCnt] = money;
        } else {
            return;
        }

        if (customerCnt >= c) {
            answer = Math.min(answer, money);
            return;
        }

        for (int i = 0; i < n; i++) {
            City city = list.get(i);
            dfs(money + city.money, customerCnt + city.customerCnt);
        }
    }

    static class City {
        int money;
        int customerCnt;

        public City(int money, int customerCnt) {
            this.money = money;
            this.customerCnt = customerCnt;
        }
    }
}





