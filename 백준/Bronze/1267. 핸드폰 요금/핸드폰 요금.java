
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        int[] arr = new int[count];
        int y = 0;
        int m = 0;

        for(int i = 0; i < count; i++) {
            arr[i] += sc.nextInt();
            y += (arr[i] / 30 + 1) * 10;
            m += (arr[i] / 60 + 1) * 15;
        }

        if(y > m) {
            System.out.println("M " + m);
        } else if(y < m){
            System.out.println("Y " + y);
        } else {
            System.out.println("Y M " + y);
        }
    }
}