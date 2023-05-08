
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();

        Queue<Integer> que = new LinkedList<>();
        int last = 0;

        for(int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            switch (s) {
                case "push":
                    last = Integer.parseInt(st.nextToken());
                    que.offer(last);
                    break;
                case "pop":
                    if(que.isEmpty()) {
                        sb.append(-1).append('\n');
                    } else {
                        sb.append(que.poll()).append('\n');
                    }
                    break;
                case "size":
                    sb.append(que.size()).append('\n');
                    break;
                case "empty":
                    if(que.isEmpty()) {
                        sb.append(1).append('\n');
                    } else {
                        sb.append(0).append('\n');
                    }
                    break;
                case "front":
                    if(que.isEmpty()) {
                        sb.append(-1).append('\n');
                    } else {
                        sb.append(que.peek()).append('\n');
                    }
                    break;
                case "back":
                    if(que.isEmpty()) {
                        sb.append(-1).append('\n');
                    } else {
                        sb.append(last).append('\n');
                    }
            }
        }
        System.out.println(sb);
    }
}