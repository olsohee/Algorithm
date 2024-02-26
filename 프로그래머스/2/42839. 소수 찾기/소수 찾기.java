import java.util.*;

class Solution {
        String numbers;
        boolean[] visited;
        int answer = 0;
        Set<Integer> set = new HashSet<>();

        public int solution(String numbers) {
            this.numbers = numbers;
            visited = new boolean[numbers.length()];

            for (int i = 1; i <= numbers.length(); i++) {
                func("", i, visited);
            }

            for (Integer num : set) {
                if (isPrime(num)) {
                    answer++;
                }
            }
            return answer;
        }

        private void func(String str, int len, boolean[] visited) {
            if (str.length() == len) {
                set.add(Integer.parseInt(str));
                return;
            }

            for (int i = 0; i < numbers.length(); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    func(str + String.valueOf(numbers.charAt(i)), len, visited);
                    visited[i] = false;
                }
            }
        }

        private boolean isPrime(Integer num) {
            if (num == 0 || num == 1) return false;

            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    return false;
                }
            }

            return true;
        }
}