import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int answer = 0;
        int target = Integer.parseInt(br.readLine());
        
        while (true) {
            if (target % 5 == 0) {
                answer += target / 5;
                break;
            } else {
                target -= 3;
                answer++;
            }
            if (target < 0) {
                answer = -1;
                break;
            }
        }
        
        System.out.println(answer); // 불가능하면 -1 출력
    }
}