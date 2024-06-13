import java.util.*;


class Solution {

    String[][] graph = new String[51][51];
    Node[][] parent = new Node[51][51];

    public String[] solution(String[] commands) {

        // 부모 초기화
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                parent[i][j] = new Node(i, j);
            }
        }

        List<String> list = new ArrayList<>();

        for (String command : commands) {
            
            // printMap();
            
            String[] arr = command.split(" ");
            if (arr[0].equals("UPDATE")) {
                if (arr.length == 4) {
                    command1(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), arr[3]);
                }
                if (arr.length == 3) {
                    command2(arr[1], arr[2]);
                }
            }
            if (arr[0].equals("MERGE")) {
                command3(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]),
                        Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
            }
            if (arr[0].equals("UNMERGE")) {
                command4(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
            }
            if (arr[0].equals("PRINT")) {
                int y = Integer.parseInt(arr[1]);
                int x = Integer.parseInt(arr[2]);
                if (graph[y][x] == null) {
                    list.add("EMPTY");
                } else {
                    list.add(graph[y][x]);
                }
            }
        }

        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    private void printMap() {
        
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
     System.out.println();
        System.out.println();
    }

    private Node find(int r, int c) {
        if (parent[r][c].y == r && parent[r][c].x == c) {
            return parent[r][c];
        }

        return parent[r][c] = find(parent[r][c].y, parent[r][c].x); // todo
    }

    private void command1(int r, int c, String value) {
        // 최상위 부모 찾기
        Node p = find(r, c);

        // 최상위 부모와 그 자식들의 값 변경
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (find(i, j).equals(p)) {
                    graph[i][j] = value;
                }
            }
        }
    }

    private void command2(String value1, String value2) {
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (graph[i][j] != null && graph[i][j].equals(value1)) {
                    graph[i][j] = value2;
                }
            }
        }
    }

    private void command3(int r1, int c1, int r2, int c2) {
        if (graph[r1][c1] != null) {
            union(r1, c1, r2, c2);
        } else {
            union(r2, c2, r1, c1);
        }
    }

    private void union(int r1, int c1, int r2, int c2) {
        String value = graph[r1][c1];
        Node baseP = find(r1, c1);

        // r2, c2의 최상위 노드가 baseP를 가리키도록
        Node p = find(r2, c2);
        parent[p.y][p.x] = baseP;

        // r2, c2의 최상위 노드의 자식 노드들의 값 변경
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (find(i, j).equals(baseP)) {
                    graph[i][j] = value;
                }
            }
        }
    }

    private void command4(int r, int c) {
        String value = graph[r][c];
        Node p = find(r, c);
        for (int i = 1; i < 51; i++) {
            for (int j = 1; j < 51; j++) {
                if (find(i, j).equals(p)) {
                    parent[i][j] = new Node(i, j);
                    graph[i][j] = null;
                }
            }
        }
        graph[r][c] = value;
    }

    private static class Node {

        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return y == node.y && x == node.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }
}


// 병합된 노드끼리는 모두 같은 최상위 부모 노드를 갖는다. (find메소드로 최상위 부모를 찾기)
// 같은 무리이면 같은 값을 갖는다.
    // 노드 1, 2가 같은 무리이고, 3, 4가 같은 무리일 때
    // 1과 4를 합치면, 
    // 4의 최상위 부모를 1로 바꾸기 + 4의 최상위 부모의 모든 자식들을 1의 값으로 바꾸기