import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<List<Integer>> list = new ArrayList<>();
    static int removeNode;
    static int answer;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        int root = 0;
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                list.get(parent).add(i);
            }
        }

        removeNode = Integer.parseInt(br.readLine());

        // 루트 노드부터 remove 시작해서 리프 노드 개수 세기
        answer = findParentNode(root);

        System.out.println(answer);
    }

    private static int findParentNode(int now) {
        // 지워진 노드이면, 해당 노드와 그 자식 노드 탐색 X
        if (now == removeNode) {
            return 0;
        }

        // 루트노드인 경우
        if (list.get(now).size() == 0) {
            return 1;
        }

        int sum = 0;
        for (int next : list.get(now)) {
            sum += findParentNode(next);
        }

        if (sum > 0) {
            return sum; // 현재 노드의 자식 노드가 리프 노드
        } else {
            return 1; // 현재 노드가 리프 노드
        }
    }
}