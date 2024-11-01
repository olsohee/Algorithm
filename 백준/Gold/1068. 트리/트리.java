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

        // 아래로 내려가며 탐색할거니까 단방향이어도 됨 (부모 -> 자식)
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
        if (removeNode == root) {
            System.out.println(0);
        } else {
            findParentNode(root);
            System.out.println(answer);
        }
    }

    private static void findParentNode(int now) {

        int nodeCnt = 0; // 자식 노드의 수

        for (int next : list.get(now)) {
            if (next != removeNode) {
                findParentNode(next);
                nodeCnt++;
            }
        }

        if (nodeCnt == 0) {
            answer++;
        }
    }
}
