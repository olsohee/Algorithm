import java.util.Stack;
class Solution {

    Stack<Integer> stack = new Stack<>();

    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        stack.push(0);
        for (int move : moves) {
            for(int i = 0; i < board.length; i++) {
                if(board[i][move - 1] != 0) {
                    int doll = board[i][move - 1];

                    // 인형 빼기
                    board[i][move - 1] = 0;

                    // 인형 넣기 전에 터트릴 수 있나 확인
                    if(stack.peek() == doll) {
                        // 터트리기
                        stack.pop();
                        answer++;
                    } else {
                        // 인형 넣기
                        stack.push(doll);
                    }
                    break;
                }
            }
        }

        return answer * 2;
    }
}
