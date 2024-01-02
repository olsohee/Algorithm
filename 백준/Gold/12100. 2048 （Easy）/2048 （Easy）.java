import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

// 시간복잡도: 4^5 + O(N^2) (dfs + 그래프 탐색) = 4^5 + 400
public class Main {

    static int n;
    static int[][] map;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        int[][] newMap = up(map);
//        for (int[] ints : newMap) {
//            System.out.println();
//            for (int anInt : ints) {
//                System.out.print(anInt + " ");
//            }
//        }

        dfs(0, copyMap(map));
        System.out.println(answer);
    }

    private static void dfs(int count, int[][] map) {
        if (count == 5) {
            answer = Math.max(getMax(map), answer);
            return;
        }

        // 위
        dfs(count + 1, up(copyMap(map)));

        // 아래
        dfs(count + 1, down(copyMap(map)));

        // 오른쪽
        dfs(count + 1, right(copyMap(map)));

        // 왼쪽
        dfs(count + 1, left(copyMap(map)));
    }

    private static int[][] up(int[][] map) {
        int[][] newMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            int lastIdx = 0;
            int checkIdx = 0;
            for (int j = 0; j < n; j++) {
                int num = map[j][i];
                if (num == 0) continue;
                // 합치는 경우
                if (newMap[checkIdx][i] == num) {
                    newMap[checkIdx][i] = num * 2;
                    checkIdx++;
                }
                // 합치지 않는 경우
                else {
                    newMap[lastIdx][i] = num;
                    lastIdx++;
                    checkIdx = lastIdx - 1;
                }
            }
        }
        return newMap;
    }

    private static int[][] down(int[][] map) {
        int[][] newMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            int lastIdx = n - 1;
            int checkIdx = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                int num = map[j][i];
                if (num == 0) continue;
                if (newMap[checkIdx][i] == num) {
                    newMap[checkIdx][i] = num * 2;
                    checkIdx--;
                } else {
                    newMap[lastIdx][i] = num;
                    lastIdx--;
                    checkIdx = lastIdx + 1;
                }
            }
        }
        return newMap;
    }

    private static int[][] right(int[][] map) {
        int[][] newMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            int lastIdx = n - 1;
            int checkIdx = n - 1;
            for (int j = n - 1; j >= 0; j--) {
                int num = map[i][j];
                if (num == 0) continue;
                if (newMap[i][checkIdx] == num) {
                    newMap[i][checkIdx] = num * 2;
                    checkIdx--;
                } else {
                    newMap[i][lastIdx] = num;
                    lastIdx--;
                    checkIdx = lastIdx + 1;
                }
            }
        }
        return newMap;
    }

    private static int[][] left(int[][] map) {
        int[][] newMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            int lastIdx = 0;
            int checkIdx = 0;
            for (int j = 0; j < n; j++) {
                int num = map[i][j];
                if (num == 0) continue;
                if (newMap[i][checkIdx] == num) {
                    newMap[i][checkIdx] = num * 2;
                    checkIdx++;
                } else {
                    newMap[i][lastIdx] = num;
                    lastIdx++;
                    checkIdx = lastIdx - 1;
                }
            }
        }
        return newMap;
    }

    private static int getMax(int[][] map) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }

    private static int[][] copyMap(int[][] map) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            copy[i] = map[i];
        }
        return copy;
    }
}