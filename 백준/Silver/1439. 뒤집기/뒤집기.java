
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] charArr = br.readLine().toCharArray();

        int zeroCnt = 0;
        int oneCnt = 0;

        if (charArr[0] == '0') {
            zeroCnt++;
        } else {
            oneCnt++;
        }

        for (int i = 1; i < charArr.length; i++) {
            if (charArr[i - 1] != charArr[i]) {
                if (charArr[i] == '0') {
                    zeroCnt++;
                } else {
                    oneCnt++;
                }
            }
        }

        System.out.println(Math.min(zeroCnt, oneCnt));
    }
}
