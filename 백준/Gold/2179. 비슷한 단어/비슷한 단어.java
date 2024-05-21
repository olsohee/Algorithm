import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(br.readLine());
        }

        int sameCountMax = 0;
        int idx1 = -1;
        int idx2 = -1;

        // 두 단어씩 비교
        for (int i = 0; i < n - 1; i++) {
            String str1 = list.get(i);
            for (int j = i + 1; j < n; j++) {
                String str2 = list.get(j);
                int len = Math.min(str1.length(), str2.length());
                int sameCount = 0;
                for (int k = 0; k < len; k++) {
                    if (str1.charAt(k) != str2.charAt(k)) {
                        break;
                    }
                    sameCount++;
                }
                if (sameCount > sameCountMax) {
                    sameCountMax = sameCount;
                    idx1 = i;
                    idx2 = j;
                }
            }
        }

        System.out.println(list.get(idx1));
        System.out.println(list.get(idx2));
    }
}

