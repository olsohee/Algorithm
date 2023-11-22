
class Solution {
    int[][] sumGraph;
    int[][] board;
    int[][] skill;

    public int solution(int[][] board, int[][] skill) {
        this.board = board;
        this.skill = skill;
        int N = board.length; // 높이
        int M = board[0].length; // 가로 길이

        sumGraph = new int[N + 1][M + 1];

        // 각 skill 돌면서, 공격 또는 회복하기 (누적합 그래프 생성)
        for(int i = 0; i < skill.length; i++) {
            int[] oneSkill = skill[i];
            int y1 = oneSkill[1]; int x1 = oneSkill[2];
            int y2 = oneSkill[3]; int x2 = oneSkill[4];
            int degree = oneSkill[5];
            degree = (oneSkill[0] == 1) ? degree * -1 : degree * 1;
            sumGraph[y1][x1] += degree;
            sumGraph[y2 + 1][x1] -= degree;
            sumGraph[y1][x2 + 1] -= degree;
            sumGraph[y2 + 1][x2 + 1] += degree;
        }

        // 누적합 그래프 위아래로 합치기
        // 상 하
        for(int y = 1; y < N; y++) {
            for(int x = 0; x < M; x++) {
                sumGraph[y][x] += sumGraph[y - 1][x];
            }
        }

        // 좌 우
        for(int x = 1; x < M; x++) {
            for(int y = 0; y <N; y++) {
                sumGraph[y][x] += sumGraph[y][x - 1];
            }
        }

        // 누적합 그래프와 그냥 그래프 더해주기 (누적합 반영)
        int answer = 0;
//
//        System.out.println("=========");
//        for(int i = 0; i < sumGraph.length; i++) {
//            for(int j = 0; j < sumGraph[i].length; j++) {
//                System.out.print(sumGraph[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("=========");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(board[i][j] +  sumGraph[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
