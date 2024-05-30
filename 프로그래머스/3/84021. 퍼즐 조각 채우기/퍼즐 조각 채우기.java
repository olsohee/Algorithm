import java.util.*;


class Solution {

    int n;
    int[][] game_board;
    int[][] table;
    boolean[][] visited_table;
    boolean[][] visited_board;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    List<int[][]> puzzleList = new ArrayList<>();
    List<int[][]> boardList = new ArrayList<>();
    int answer;
    public int solution(int[][] game_board, int[][] table) {

        this.game_board = game_board;
        this.table = table;

        n = game_board.length;
        visited_table = new boolean[n][n];
        visited_board = new boolean[n][n];

        // 1. bfs로 퍼즐 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] == 1 && !visited_table[i][j]) {
                    bfsForPuzzle(i, j);
                }
            }
        }

        // 2. 게임 보드의 빈 칸 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (game_board[i][j] == 0 && !visited_board[i][j]) {
                    bfs2(i, j);
                }
            }
        }

        // 3. 보드에 퍼즐 넣기
        for (int[][] board : boardList) {
            for (int[][] puzzle : puzzleList) {
                boolean result = putPuzzle(board, puzzle);
                if (result) {
                    puzzleList.remove(puzzle);
                    break;
                }
            }
        }

        return answer;
    }


    public void bfsForPuzzle(int y, int x) {
        List<Node> list = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        visited_table[y][x] = true;
        list.add(new Node(y, x));
        int minX = x;
        int minY = y;
        int maxX = x;
        int maxY = y;

        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || table[ny][nx] != 1 || visited_table[ny][nx]) {
                    continue;
                }
                que.add(new Node(ny, nx));
                visited_table[ny][nx] = true;
                list.add(new Node(ny, nx));
                minX = Math.min(minX, nx);
                maxX = Math.max(maxX, nx);
                minY = Math.min(minY, ny);
                maxY = Math.max(maxY, ny);
            }
        }

        // list에 있는 값들을 통해 딱 맞는 배열 만들기 (퍼즐 위치한 곳 = 1)
        int[][] puzzle = new int[maxY - minY + 1][maxX - minX + 1];
        for (Node node : list) {
            puzzle[node.y - minY][node.x - minX] = 1;
        }

        puzzleList.add(puzzle);
    }

    private boolean putPuzzle(int[][] board, int[][] puzzle) {
//        System.out.println("==================");
//        System.out.println("board = " + board.length + ", " + board[0].length);
//        System.out.println("puzzle = " + puzzle.length + ", " + puzzle[0].length);

        for (int i = 0; i < 4; i++) {
            if (canPut(board, puzzle)) {
//                System.out.println("true!");
                return true;
            } else {
                puzzle = turn(puzzle); // puzzle 변수는 새로운 배열을 가리킴
//                System.out.println("turn");
            }
        }
//        System.out.println("false");
        return false;
    }

    private int[][] turn(int[][] puzzle) {
        // 회전한 새로운 배열을 만들어서 반환
        int[][] newPuzzle = new int[puzzle[0].length][puzzle.length];
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[0].length; j++) {
                if (puzzle[i][j] == 1) {
                    newPuzzle[j][puzzle.length - i - 1] = 1;
                }
            }
        }
        return newPuzzle;
    }

    private boolean canPut(int[][] board, int[][] puzzle) {
        // 사이즈가 맞는지
        int boardY = board.length;
        int boardX = board[0].length;
        int puzzleY = puzzle.length;
        int puzzleX = puzzle[0].length;

        if ((boardY != puzzleY) || (boardX != puzzleX)) {
            return false;
        }

        // 칸이 맞는지
        int cnt = 0;
        for (int i = 0; i < boardY; i++) {
            for (int j = 0; j < boardX; j++) {
                if (board[i][j] != puzzle[i][j]) {
                    return false;
                }
                if (puzzle[i][j] == 1) {
                    cnt++;
                }
            }
        }
//        System.out.println("cnt = " + cnt);
        answer += cnt;
        return true;
    }

    public void bfs2(int y, int x) {
        List<Node> list = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        que.add(new Node(y, x));
        visited_board[y][x] = true;
        list.add(new Node(y, x));
        int minX = x;
        int minY = y;
        int maxX = x;
        int maxY = y;

        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + now.x;
                int ny = dy[i] + now.y;
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || game_board[ny][nx] != 0 || visited_board[ny][nx]) {
                    continue;
                }
                que.add(new Node(ny, nx));
                visited_board[ny][nx] = true;
                list.add(new Node(ny, nx));
                minX = Math.min(minX, nx);
                maxX = Math.max(maxX, nx);
                minY = Math.min(minY, ny);
                maxY = Math.max(maxY, ny);
            }
        }

        // list에 있는 값들을 통해 딱 맞는 배열 만들기 (빈 칸 = 1)
        int[][] board = new int[maxY - minY + 1][maxX - minX + 1];
        for (Node node : list) {
            board[node.y - minY][node.x - minX] = 1;
        }

        boardList.add(board);
    }


    public class Node {

        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
