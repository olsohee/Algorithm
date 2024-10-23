import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        List<Puddle> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Puddle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list, (o1, o2) -> o1.start - o2.start);

        int last = -1; // 널빤지의 마지막 위치
        int cnt = 0;
        for (Puddle puddle : list) {
        
            // 이전 널빤지로 커버가 다 되는 경우
            if (puddle.end - 1 <= last) {
                continue;
            }
            
            // 이전 널빤지와 겹치는 경우
            if (puddle.start <= last) {
                puddle.start = last + 1;
            }

            cnt += (puddle.end - puddle.start) / l;
            int remain = (puddle.end - puddle.start) % l;
            if (remain != 0) {
                cnt++;
                last = puddle.end - 1 + (l - remain);
            } else {
                last = puddle.end - 1;
            }
        }

        System.out.println(cnt);

    }

    static class Puddle {
        int start;
        int end;

        public Puddle(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
