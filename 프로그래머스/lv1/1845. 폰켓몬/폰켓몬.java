import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
         HashSet<Integer> hashSet = new HashSet<>();

        // 중복을 제거하여 저장
        for(int i = 0; i < nums.length; i++) {
            hashSet.add(nums[i]);
        }

        if(hashSet.size() >= nums.length / 2) {
            return nums.length / 2;
        } else {
            return hashSet.size();
        }
    }
}