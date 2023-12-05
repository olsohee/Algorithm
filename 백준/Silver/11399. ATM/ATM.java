
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] persons = new int[n];
        int[] times = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            persons[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(persons);
        times[0] = persons[0];
        int answer = times[0];
        for (int i = 1; i < n; i++) {
            times[i] = persons[i] + times[i - 1];
            answer += times[i];
        }

        System.out.println(answer);
    }
}
