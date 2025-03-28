import java.util.*;

class Solution {
    
    Node[] tree;
    int[] parent;
    int root;
    int[] num;
    int size;
    
    public int solution(int k, int[] num, int[][] links) {
        setTree(num, links);
        this.num = num;
        
        int start = 0;
        int end = 0;
        for (int i = 0; i < num.length; i++) {
            start = Math.max(start, num[i]);
            end += num[i];
        }
        
        while (start <= end) {
            int mid = (start + end) / 2;
            if (getGroupCnt(mid) <= k) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        return start;
    }
    
    private int getGroupCnt(int max) { // max: 한 그룹의 최대 인원수
        size = 0; // 자르는 개수
        dfs(max, root);
        return size + 1; // size 만큼 잘랐으면 그룹 개수는 size + 1개
    }
    
    private int dfs(int max, int now) {
        int leftSum = 0;
        int rightSum = 0;
        
        if (tree[now].left != -1) {
            leftSum = dfs(max, tree[now].left);
        }
        
        if (tree[now].right != -1) {
            rightSum = dfs(max, tree[now].right);
        }
        
        if (num[now] + leftSum + rightSum <= max) {
            return num[now] + leftSum + rightSum;
        }
        if (num[now] + Math.min(leftSum, rightSum) <= max) {
            size++;
            return num[now] + Math.min(leftSum, rightSum);
        }
        size += 2;
        return num[now];
    }
    
    private void setTree(int[] num, int[][] links) {
        tree = new Node[num.length];
        parent = new int[num.length];
        
        Arrays.fill(parent, -1);
        
        for (int i = 0; i < links.length; i++) {
            tree[i] = new Node(links[i][0], links[i][1]);
            
            if (tree[i].left != -1) {
                parent[tree[i].left] = i;
            }
            if (tree[i].right != -1) {
                parent[tree[i].right] = i;
            }
        }
        
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1) {
                root = i;
                break;
            }
        }
    }
    
    class Node {
        int left, right;
        
        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}