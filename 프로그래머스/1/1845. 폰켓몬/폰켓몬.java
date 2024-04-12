import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int goal = nums.length / 2;
        Set<Integer> set = new HashSet<>(); // 포켓몬 종류를 담는 set
        for (int num : nums) {
            set.add(num);
        }
        
        if (goal <= set.size()) return goal;
        else return set.size();
    }
}