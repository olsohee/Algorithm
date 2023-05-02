import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[4];

        for(int i = 0; i < 3; i++) {

            int count = 0; //등(1)의 갯수

            //입력받기
            for(int j = 0; j < arr.length; j++) {
                arr[i] = sc.nextInt();
                if(arr[i] == 1) count++;
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