
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

        long answer = 0;
        int delivery = cap;
        int pickup = cap;

        for(int i = n - 1; i >= 0; i--) {
            if(deliveries[i] != 0 || pickups[i] != 0) {
                answer += (i + 1) * 2;
                break;
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            if(deliveries[i] == 0 && pickups[i] == 0) {
                continue;
            }
//            System.out.println("===========");
//            System.out.println("i = " + i);
            delivery -= deliveries[i];
            pickup -= pickups[i];

            while (delivery < 0 || pickup < 0) {
                answer += (i+1) * 2;
                delivery += cap;
                pickup += cap;
            }
//            System.out.println("delivery = " + delivery);
//            System.out.println("pickup = " + pickup);
//            System.out.println("answer = " + answer);
        }

        return answer;
    }
}