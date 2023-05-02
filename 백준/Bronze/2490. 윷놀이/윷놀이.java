
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 3; i++) {
            int count = 0; //등(1)의 갯수

            //입력받기
            for(int j = 0; j < 4; j++) {
                int input = sc.nextInt();
                if(input == 1) count++;
            }

            switch (count) {
                case 4:
                    System.out.println("E");
                    break;
                case 3:
                    System.out.println("A");
                    break;
                case 2:
                    System.out.println("B");
                    break;
                case 1:
                    System.out.println("C");
                    break;
                default:
                    System.out.println("D");
            }
        }
    }
}
