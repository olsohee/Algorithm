
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            list.add(i);
        }

        LinkedList<Integer> result = new LinkedList<>();
        int index = 0;
        while (list.size() > 0)
        {
            index = (index + k - 1) % list.size();
            result.add(list.remove(index));
        }

        System.out.println(result.toString().replace('[', '<').replace(']', '>'));
    }
}