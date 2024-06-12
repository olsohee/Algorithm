import java.util.*;

class Solution {
    public int solution(int[] arrows) {
        
        int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int answer = 0;
        
        Map<Node, List<Node>> visited = new HashMap<>();
        Node now = new Node(0, 0);
        
        for (int arrow : arrows) {
            for (int i = 0; i < 2; i++) {
                Node next = new Node(now.y + dy[arrow], now.x + dx[arrow]);
                if (!visited.containsKey(next)) {
                    visited.put(next, getList(now));
                    List<Node> list = visited.getOrDefault(now, new ArrayList<>());
                    list.add(next);
                    visited.put(now, list);
                    // if (visited.get(now) == null) {
                    //     visited.put(now, getList(next));
                    // } else {
                    //     visited.get(now).add(next);
                    // }
                } else if (!visited.get(next).contains(now)) { // 재방문한 노드 && 처음 가는 간선 -> 방 생성
                    visited.get(next).add(now);
                    visited.get(now).add(next);
                    answer++;
                }
                now = next;
            }
            
            
        }
    
        return answer;
    }
    
    private List<Node> getList(Node node) {
        List<Node> list = new ArrayList<>();
        list.add(node);
        return list;
    }
    
    private static class Node {
        
        int y, x;
        
        public Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }
 
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}