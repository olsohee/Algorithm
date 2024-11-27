import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        List<Integer> primeNumbers = makePrimeNumber(b);
        int answer = 0;
        for (int i = a; i <= b; i++) {
            if (isUnderPrime(i, primeNumbers)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static boolean isUnderPrime(int number, List<Integer> primeNumbers) {
        int cnt = 0; // 소수 개수
        for (int num : primeNumbers) {
            if (number < num) {
                break;
            }
            while (number % num == 0) {
                number /= num;
                cnt++;
            }
        }

        return isPrime(cnt); // 소수의 개수가 소수인지 판단
    }

    private static List<Integer> makePrimeNumber(int max) {
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= max; i++) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }

    private static boolean isPrime(int number) {
        if (number == 1) return false;
        if (number == 2) return true;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}