import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        String str = Integer.toString(a * b * c);

        int[] numArr = new int[10];
        for(int i = 0; i < str.length(); i++){
            numArr[str.charAt(i) - '0']++;
        }

        for (int i = 0; i < numArr.length; i++) {
            System.out.println(numArr[i]);
        }
    }
}