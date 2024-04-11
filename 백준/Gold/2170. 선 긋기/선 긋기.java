import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        
        int n = Integer.parseInt(br.readLine());
        List<Line> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))); 
        }
        
        // 시작을 기준으로 정렬
        Collections.sort(list);
        
        int start = list.get(0).start;
        int end = list.get(0).end;
        
        for (int i = 1; i < list.size(); i++) {
            Line line = list.get(i);
            // 시작이 start~end 범위에 들면, end 갱신
            if (line.start <= end) {
                end = Math.max(end, line.end);
            } 
            // 시작이 end보다 크면, 기존 start~end를 답에 반영하고 새로운 start, end 만들기
            else {
                answer += (end - start);
                start = line.start;
                end = line.end;
            }
        }
        answer += (end - start);
        System.out.println(answer);
    }
    
    static class Line implements Comparable<Line> {
        int start, end;
        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        // 시작이 빠른 순으로 정렬
        @Override
        public int compareTo(Line o) {
            return this.start - o.start;
        }
    }
}