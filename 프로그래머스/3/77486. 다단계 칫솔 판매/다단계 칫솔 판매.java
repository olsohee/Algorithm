import java.util.*;

class Solution {

    String[] enroll;
    int[] answer;
    int[] parent;
    Map<String, Integer> idxMapByName = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        this.enroll = enroll;
        answer = new int[enroll.length];
        parent = new int[enroll.length];

        for (int i = 0; i < enroll.length; i++) {
            idxMapByName.put(enroll[i], i);
        }

        for (int i = 0; i < referral.length; i++) {
            if (referral[i].equals("-")) {
                parent[i] = -1;
            } else {
                parent[i] = idxMapByName.get(referral[i]);
            }
        }

        // 수익금 계산
        for (int i = 0; i < seller.length; i++) {
            dfs(idxMapByName.get(seller[i]), amount[i] * 100);
        }

        return answer;
    }

    private void dfs(int nameIdx, int money) {
        int parentMoney = (int)(money * 0.1);
        if (parent[nameIdx] == -1) {
            if (parentMoney == 0) {
                answer[nameIdx] += money;
            } else {
                answer[nameIdx] += money - parentMoney;
            }
            return;
        }

        if (parentMoney == 0) {
            answer[nameIdx] += money;
        } else {
            answer[nameIdx] += money - parentMoney;
            dfs(parent[nameIdx], parentMoney);
        }
    }
}
