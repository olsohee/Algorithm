class Solution {
    int answer = 0;
    public int solution(int[] numbers, int target) {
        
        func(0, 0, numbers, target);
        return answer;
    }
    
    private void func(int cnt, int sum, int[] numbers, int target) {
        
        if(cnt == numbers.length) {
            if(sum == target) {
                answer++;
            }
            return;
        }
        
        sum += numbers[cnt];
        func(cnt + 1, sum, numbers, target);

        sum -= numbers[cnt];
        sum -= numbers[cnt];
        func(cnt + 1, sum, numbers, target);
    }
}