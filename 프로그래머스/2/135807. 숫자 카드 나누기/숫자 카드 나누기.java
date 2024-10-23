import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        /*
        arrayA의 최대 공약수 -> arrayB는 나눌 수 없음
        arrayB의 최대 공약수 -> arrayA는 나눌 수 없음
        
        최대 공약수 구하기: 재귀
        재귀로 나온 최대 공약수를 다른 array와 비교하기 
        */
        int answer = 0;
        
        Arrays.sort(arrayA);
        int gcdA = arrayA[0];
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = func(gcdA, arrayA[i]);
        }
         
        if (canResult(gcdA, arrayB)) {
            answer = Math.max(answer, gcdA);
        }
        
        Arrays.sort(arrayB);
        int gcdB = arrayB[0];
        for (int i = 1; i < arrayB.length; i++) {
            gcdB = func(gcdB, arrayB[i]);
        }
         
        if (canResult(gcdB, arrayA)) {
            answer = Math.max(answer, gcdB);
        }
        
        return answer;
    }
    
    private int func(int n1, int n2) {
        if (n2 % n1 == 0) return n1;
        return func(n2 % n1, n1);
    }
    
    private boolean canResult(int num, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % num == 0) return false;
        }
        return true;
    }
}