import java.util.*;

class Solution {
    
    int n; // 포인트 개수
    int x; // 로봇 개수
    int m; // 로봇이 방문할 포인트 개수
    Map<Integer, List<Node>> map = new HashMap<>(); // 각 로봇이 방문할 포인트
    Map<Integer, List<Node>> totalRoute = new HashMap<>();
    
    public int solution(int[][] points, int[][] routes) {
        n = points.length;
        x = routes.length;
        for (int i = 0; i < x; i++) {
            map.put(i, new ArrayList<>());
        }
    
        m = routes[0].length;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < m; j++) {
                int point = routes[i][j];
                map.get(i).add(new Node(points[point - 1][0], points[point - 1][1]));
            }
        }
        
        // 1. 각 로봇별 경로 구하기
        for (int i = 0; i < x; i++) { // i번 로봇의 총 경로 구하기
            List<Node> route = new ArrayList<>();
            List<Node> list = map.get(i);
            route.add(list.get(0));
            for (int j = 0; j < list.size() - 1; j++) {
                Node start = list.get(j);
                Node end = list.get(j + 1);
                move(route, start, end);
            }
            totalRoute.put(i, route);
        }
        
        // 2. 충돌 횟수 구하기
        int answer = 0;
        int time = 0;
        while (true) {
            boolean isFinish = true;
            Map<Integer, Integer> duplicatedMap = new HashMap<>();
            for (int i = 0; i < x; i++) {
                if (totalRoute.get(i).size() <= time) {
                    continue;
                }
                isFinish = false;
                int nowLocation = totalRoute.get(i).get(time).y * 100 + totalRoute.get(i).get(time).x; 
                duplicatedMap.put(nowLocation, duplicatedMap.getOrDefault(nowLocation, 0) + 1); 
            }
            for (int location : duplicatedMap.keySet()) {
                if (duplicatedMap.get(location) > 1) {
                    answer++;
                }
            }
            if (isFinish) break;
            time++;
        }
        
        return answer;
    }
    
    public void move(List<Node> route, Node start, Node end) {
        int ny = start.y;
        int nx = start.x;
        
        int diff = start.y - end.y;
        while (diff != 0) {
            if (diff > 0) {
                ny--;
                diff--;
            } else {
                ny++;
                diff++;
            }
            route.add(new Node(ny, nx));
        }
        
        diff = start.x - end.x;
        while (diff != 0) {
            if (diff > 0) {
                nx--;
                diff--;
            } else {
                nx++;
                diff++;
            }
            route.add(new Node(ny, nx));
        }
    }
    
    class Node {
        int y, x;
        public Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}