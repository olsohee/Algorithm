import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        List<Integer> craneList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            craneList.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        List<Integer> boxList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            boxList.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(craneList, Collections.reverseOrder());
        Collections.sort(boxList, Collections.reverseOrder());

        if (boxList.get(0) > craneList.get(0)) {
            System.out.println(-1);
            return;
        }

        int answer = 0;
        while (!boxList.isEmpty()) {
            int boxIdx = 0;
            int craneIdx = 0;
            while (craneIdx < n) {
                if (boxIdx >= boxList.size()) {
                    break;
                }
                if (craneList.get(craneIdx) >= boxList.get(boxIdx)) {
                    boxList.remove(boxIdx);
                    craneIdx++;
                } else {
                    boxIdx++;
                }
            }
            answer++;
        }

        System.out.println(answer);
    }
}