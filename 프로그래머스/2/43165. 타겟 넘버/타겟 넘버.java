class Solution {
        int answer = 0;
        int[] numbers;
        int target = 0;

        public int solution(int[] numbers, int target) {

            this.numbers = numbers;
            this.target = target;

            dfs(0, 0);
            return answer;
        }

        private void dfs(int cnt, int sum) {

            if(cnt == numbers.length) {
                if(sum == target) {
                    answer++;
                }
                return;
            }

            dfs(cnt + 1, sum + numbers[cnt]);
            dfs(cnt + 1, sum - numbers[cnt]);
        }
    }