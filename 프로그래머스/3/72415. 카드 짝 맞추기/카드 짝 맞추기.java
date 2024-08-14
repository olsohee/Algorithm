import java.util.*;

class Solution {

    int[][] board;
    int r, c;
    List<List<Integer>> combinationList = new ArrayList<>();
    int nowY, nowX;

    public int solution(int[][] board, int r, int c) {
        this.board = board;
        this.r = r;
        this.c = c;

        // 1. 카드 뽑는 순서의 조합 만들기
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    set.add(board[i][j]);
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        for (Integer integer : set) {
            list.add(integer);
        }

        dfs(list, new ArrayList<>(), new boolean[list.size()]);


        // 2. 각 조합마다 키 조작 횟수 구하기
        int answer = Integer.MAX_VALUE;
        for (List<Integer> combination : combinationList) {
//            System.out.println("======조합======");
//            for (Integer integer : combination) {
//                System.out.print(integer + " ");
//            }
//            System.out.println();

            nowY = r;
            nowX = c;
            int[][] copyMap = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    copyMap[i][j] = board[i][j];
                }
            }
            int cntSum = 0;
            for (int i = 0; i < combination.size(); i++) {
//                System.out.println("찾는 번호: " + combination.get(i));
                int result = bfs(combination.get(i), copyMap);
                int result2 = bfs(combination.get(i), copyMap);
//                System.out.println(result);
//                System.out.println(result2);
                cntSum += result;
                cntSum += result2;
            }
            cntSum += combination.size() * 2; // 엔터
//            System.out.println("cntSum = " + cntSum);
            answer = Math.min(answer, cntSum);
        }

        return answer;
    }

    private int bfs(int num, int[][] copyMap) {
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        boolean[][] visited = new boolean[4][4];

        Queue<Node> que = new LinkedList<>();
        que.add(new Node(nowY, nowX, 0));
        visited[nowY][nowX] = true;

        while (!que.isEmpty()) {
            Node now = que.poll();

            // num을 찾은 경우
            if (copyMap[now.y][now.x] == num) {
                copyMap[now.y][now.x] = 0;
                nowY = now.y;
                nowX = now.x;
                return now.cnt;
            }

            // 방향키
            for (int i = 0; i < 4; i++) {
                int ny = dy[i] + now.y;
                int nx = dx[i] + now.x;
                if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) continue;
                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                que.add(new Node(ny, nx, now.cnt + 1));
            }

            // ctrl + 방향키
            for (int i = 0; i < 4; i++) {
                int ny = now.y;
                int nx = now.x;
                switch (i) {
                    case 0: // 오른쪽
                        while (true) {
                            nx++;
                            if (nx == 4) {
                                nx = 3;
                                break;
                            }
                            if (copyMap[ny][nx] != 0) {
                                break;
                            }
                        }
                        break;
                    case 1: // 왼쪽
                        while (true) {
                            nx--;
                            if (nx == -1) {
                                nx = 0;
                                break;
                            }
                            if (copyMap[ny][nx] != 0) {
                                break;
                            }
                        }
                        break;
                    case 2: // 위쪽
                        while (true) {
                            ny--;
                            if (ny == -1) {
                                ny = 0;
                                break;
                            }
                            if (copyMap[ny][nx] != 0) {
                                break;
                            }
                        }
                        break;
                    case 3: // 아래쪽
                        while (true) {
                            ny++;
                            if (ny == 4) {
                                ny = 3;
                                break;
                            }
                            if (copyMap[ny][nx] != 0) {
                                break;
                            }
                        }
                }
                if (visited[ny][nx]) continue;
                visited[ny][nx] = true;
                que.add(new Node(ny, nx, now.cnt + 1));
            }
        }

        System.out.println("오류");
        return -1;
    }

    private void dfs(List<Integer> list, List<Integer> combination, boolean[] visited) {

        if (combination.size() == list.size()) {
            List<Integer> combi = new ArrayList<>();
            for (Integer integer : combination) {
                combi.add(integer);
            }
            combinationList.add(combi);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            combination.add(list.get(i));
            dfs(list, combination, visited);

            combination.remove((Object)list.get(i));
            visited[i] = false;
        }
    }

    private class Node {
        int y, x;
        int cnt;

        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}