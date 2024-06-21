import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int goalAlp = 0;
        int goalCop = 0;
        for (int[] problem : problems) {
            goalAlp = Math.max(goalAlp, problem[0]);
            goalCop = Math.max(goalCop, problem[1]);
        }
        goalAlp = Math.max(goalAlp, alp);
        goalCop = Math.max(goalCop, cop);
        
        // 해당 알고력과 코딩력을 갖는데 걸리는 최단 시간
        int[][] time = new int[goalAlp + 1][goalCop + 1];
        for (int[] arr : time) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(alp, cop));
        time[alp][cop] = 0;
        
        while (!que.isEmpty()) {
            Node now = que.poll();
            // System.out.println(now.alp + ", " + now.cop);
            // 알고리즘 공부
            int newAlp = (now.alp + 1 > goalAlp) ? goalAlp : now.alp + 1;
            if (time[newAlp][now.cop] > time[now.alp][now.cop] + 1) {
                time[newAlp][now.cop] = time[now.alp][now.cop] + 1;
                que.add(new Node(newAlp, now.cop));
            }
            
            // 코딩 공부
            int newCop = (now.cop + 1 > goalCop) ? goalCop : now.cop + 1;
            if (time[now.alp][newCop] > time[now.alp][now.cop] + 1) {
                time[now.alp][newCop] = time[now.alp][now.cop] + 1;
                que.add(new Node(now.alp, newCop));
            }
            
            // 문제 풀기
            for (int[] problem : problems) {
                if ((now.alp >= problem[0]) && (now.cop >= problem[1])) {
                    newAlp = (now.alp + problem[2] > goalAlp) ? goalAlp : now.alp + problem[2];
                    newCop = (now.cop + problem[3] > goalCop) ? goalCop : now.cop + problem[3];
                    if (time[newAlp][newCop] > time[now.alp][now.cop] + problem[4]) {
                        time[newAlp][newCop] = time[now.alp][now.cop] + problem[4];
                        que.add(new Node(newAlp, newCop));
                    }
                }
            }
        }
        
        return time[goalAlp][goalCop];
    }
    
    private static class Node {
        
        int alp;
        int cop;
        
        public Node(int alp, int cop) {
            this.alp = alp;
            this.cop = cop;
        }
    }
}