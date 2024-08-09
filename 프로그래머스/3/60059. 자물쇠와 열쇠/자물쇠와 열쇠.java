import java.util.*;

class Solution {

    int m, n;
    int[][] key;
    int[][] lock;
    int[][] newLock;

    public boolean solution(int[][] key, int[][] lock) {

        this.key = key;
        this.lock = lock;
        m = key.length;
        n = lock.length;

        newLock = new int[(m - 1) * 2 + n][(m - 1) * 2 + n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newLock[i + (m - 1)][j + (m - 1)] = lock[i][j];
            }
        }

//        for (int[] ints : newLock) {
//            for (int anInt : ints) {
//                System.out.print(anInt + " ");
//            }
//            System.out.println();
//        }

        for (int rotateCnt = 0; rotateCnt < 4; rotateCnt++) {
            if (rotateCnt > 0) rotateKey();

            for (int i = 0; i < n + m - 1; i++) {
                for (int j = 0; j < n + m - 1; j++) {
                    boolean result = matching(i, j);
                    if (result) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean matching(int y, int x) {
//        System.out.println("y, x ==== " + y + " , " + x);
        // 자물쇠 (y, x)에 열쇠 (0, 0) 꽂기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int yLocation = m - 1 + i;
                int xLocation = m - 1 + j;
//                System.out.println(yLocation + ", " + xLocation);
//                System.out.println("-> " + newLock[yLocation][xLocation]);
                if (yLocation >= y && yLocation < y + m && xLocation >= x && xLocation < x + m) {
                    if (newLock[yLocation][xLocation] == key[yLocation - y][xLocation - x]) {
                        return false;
                    }
                } else {
                    if (newLock[yLocation][xLocation] == 0) return false;
                }
            }
        }

        return true;
    }

    private void rotateKey() {
//        System.out.println("회전!");
        int[][] newKey = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                newKey[i][j] = key[m - j - 1][i];
            }
        }
        key = newKey;
    }
}
