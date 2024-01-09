
import java.util.*;
// 시간복잡도: O(N)
class Solution {

    int answer = 0; // 체육복을 갖는 최대 학생 수
    int[] people;

    public int solution(int n, int[] lost, int[] reserve) {
        people = new int[n + 1];
        Arrays.fill(people, 1);
        for (int i : reserve) {
            people[i]++;
        }
        for (int i : lost) {
            people[i]--;
        }

        for (int i = 1; i <= n; i++) {
            if (people[i] == 2) {
                // 앞 학생에게 빌려주기
                if (i >= 2 && people[i - 1] == 0) {
                    people[i - 1] = 1;
                    people[i] = 1;
                }
                // 뒤 학생에게 빌려주기
                else if (i <= n - 1 && people[i + 1] == 0) {
                    people[i + 1] = 1;
                    people[i] = 1;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (people[i] > 0) {
                answer++;
            }
        }
        return answer;
    }
}