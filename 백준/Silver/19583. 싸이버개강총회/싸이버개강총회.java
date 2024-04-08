import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int answer = 0;
        st = new StringTokenizer(br.readLine());
        String s1 = st.nextToken();
        String s2 = st.nextToken();
        String s3 = st.nextToken();

        int time1 = Integer.parseInt(s1.split(":")[0]) * 60 + Integer.parseInt(s1.split(":")[1]);
        int time2 = Integer.parseInt(s2.split(":")[0]) * 60 + Integer.parseInt(s2.split(":")[1]);
        int time3 = Integer.parseInt(s3.split(":")[0]) * 60 + Integer.parseInt(s3.split(":")[1]);

        Set<String> set = new HashSet<>();
        String input = "";

        while((input = br.readLine()) != null) {

            int time = Integer.parseInt(input.split(" ")[0].split(":")[0]) * 60;
            time += Integer.parseInt(input.split(" ")[0].split(":")[1]);
            String name = input.split(" ")[1];

            // time1 전에 입장
            if (time <= time1) {
                set.add(name);
            }

            // time2 이후이면서 time3 이전에 퇴장
            else if (time >= time2 && time <= time3) {
                if (set.contains(name)) {
                    set.remove(name);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
