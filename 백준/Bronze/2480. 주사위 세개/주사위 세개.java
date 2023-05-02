import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        //모두 다른 경우
        if(a != b && b != c && a != c) {
            int max = a;
            if(max < b) max = b;
            if(max < c) max = c;
            System.out.println(max * 100);
        } else {
            //모두 같은 경우 또는 두개만 같은 경우
            if(a == b && a == c) { //a == b == c
                System.out.println(10000 + a * 1000);
            } else if(a == b  || a == c) { //a == b != c 이거나 a == c != b
                System.out.println(1000 + a * 100);
            } else { //b == c != a
                System.out.println(1000 + b * 100);
            }
        }
    }
}
