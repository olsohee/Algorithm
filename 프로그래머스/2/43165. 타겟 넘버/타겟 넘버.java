// 시간복잡도: O(2^20) = 1,048,576
class Solution {

        int answer;
        int[] numbers;
        int target;

        public int solution(int[] numbers, int target) {
            this.numbers = numbers;
            this.target = target;
            
            dfs(0, 0);
            return answer;
        }

        private void dfs(int sum, int idx) {
            if (idx == numbers.length) {
                if (sum == target) {
                    answer++;
                }
                return;
            }

            dfs(sum + numbers[idx], idx + 1);
            dfs(sum - numbers[idx], idx + 1);
        }
}