import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int cnt = 0;
        
        List<Node> list = new ArrayList<>();
        
        for (int i = 0; i < targets.length; i++) {
            Node node = new Node(targets[i][0], targets[i][1]);
            list.add(node);
        }
        
        Collections.sort(list);
        
        cnt = 1;
        int start = list.get(0).s;
        int end = list.get(0).e;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).s < end) {
                start = list.get(i).s;
                end = Math.min(list.get(i).e, end);
            } else {
                cnt++;
                start = list.get(i).s;
                end = list.get(i).e;
            }
        }
        
        return cnt;
    }
    
    public class Node implements Comparable<Node> {
        
        int s, e;
        
        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }
        
        @Override
        public int compareTo(Node o) {
            if (this.s == o.s) {
                return this.e - o.e;
            }
            return this.s - o.s;
        }
    }
}