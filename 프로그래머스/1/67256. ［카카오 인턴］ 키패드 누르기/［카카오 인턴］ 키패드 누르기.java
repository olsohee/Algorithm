class Solution {

    Position left = new Position(0, 3);
    Position right = new Position(2, 3);
    Position number;

    public String solution(int[] numbers, String hand) {

        String answer = "";

        for (int num : numbers) {
            number = new Position((num - 1) % 3, (num - 1) / 3);
            if(num == 0) {
                number = new Position(1, 3);
            }

            if(number.x == 0) {
                left = number;
                answer += "L";
            } else if(number.x == 2) {
                right = number;
                answer += "R";
            } else {
                int leftDistance = Math.abs(left.x - number.x) + Math.abs(left.y - number.y);
                int rightDistance = Math.abs(right.x - number.x) + Math.abs(right.y - number.y);
                if(leftDistance == rightDistance) {
                    if(hand.equals("right")) {
                        right = number;
                        answer += "R";
                    } else {
                        left = number;
                        answer += "L";
                    }
                } else if(leftDistance > rightDistance) {
                    right = number;
                    answer += "R";
                } else {
                    left = number;
                    answer += "L";
                }
            }
        }
        return answer;
    }

    class Position {
        int x, y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}