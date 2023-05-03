import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        if(num1 == num2) {
            System.out.println(0);
            return;
        }
        int max = Math.max(num1, num2);
        int min = Math.min(num1, num2);

        System.out.println(max - min - 1);

        for(int i = min + 1; i < max; i++) {
            System.out.print(i + " ");
        }
    }
}