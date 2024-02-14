import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int m;
    static int[][] map;
    static boolean[] visited;
    static List<Integer> numbers = new ArrayList<>();
    static int count = 0;
    static List<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            map[n1][n2] = 1;
            map[n2][n1] = 1;
        }
        for (int i = 1; i <= n; i++) {
            map[i][i] = 1;
        }

        // dfs로 위원회 수 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 1 && !visited[j]) {
                    count++;
                    dfs(i);
                    answerList.add(floyd(numbers));
                    numbers = new ArrayList<>();
                }
            }
        }

        System.out.println(count);
        Collections.sort(answerList);
        for (int i = 0; i < answerList.size(); i++) {
            System.out.println(answerList.get(i));
        }
    }

    private static void dfs(int start) {
        numbers.add(start);
        visited[start] = true;
        for (int i = 1; i <= n; i++) {
            if (map[start][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }

    private static int floyd(List<Integer> numbers) {
        int[][] floydMap = new int[n + 1][n + 1];
        for (int[] arr : floydMap) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        for (Integer idx : numbers) {
            for (int i = 1; i <= n; i++) {
                if (map[idx][i] != 0) {
                    floydMap[idx][i] = map[idx][i];
                }
            }
        }

//        System.out.println("=====floyd======");
//        for (int[] ints : floydMap) {
//            for (int a : ints) {
//                System.out.print(a + " ");
//            }
//            System.out.println();
//        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (floydMap[i][k] == Integer.MAX_VALUE || floydMap[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (floydMap[i][j] > floydMap[i][k] + floydMap[k][j]) {
                        floydMap[i][j] = floydMap[i][k] + floydMap[k][j];
                    }
                }
            }
        }
//        System.out.println("=====floyd======");
//        for (int[] ints : floydMap) {
//            for (int a : ints) {
//                System.out.print(a + " ");
//            }
//            System.out.println();
//        }

        // 위원장 고르기
        int minSum = Integer.MAX_VALUE;
        int chairman = 0;
        for (Integer idx : numbers) {
            int max = 0;
            for (int i = 1; i <= n; i++) {
                if (floydMap[idx][i] == Integer.MAX_VALUE) continue;
                max = Math.max(max, floydMap[idx][i]);
            }
            if (minSum > max) {
                minSum = max;
                chairman = idx;
            }
        }

        return chairman;
    }
}
