import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int L;
    static int C;
    static char[] input;
    static char[] pwd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        input = new char[C];
        pwd = new char[L];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++) {
            input[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(input);

        func(0, 0);
    }

    static void func(int count, int start) {

        if(count == L) {
            if(check(pwd)) {
                for(int i = 0; i < L; i++) {
                    System.out.print(pwd[i]);
                }
                System.out.println();
            }
            return;
        }

        for(int i = start; i < C; i++) {
            pwd[count] = input[i];
            func(count + 1, i + 1);
        }
    }

    static boolean check(char[] pwd) {
        int n1 = 0; // 자음 개수
        int n2 = 0; // 모음 개수

        for(int i = 0; i < pwd.length; i++) {
            if(pwd[i] == 'a' || pwd[i] == 'e' || pwd[i] == 'i' || pwd[i] == 'o' || pwd[i] == 'u') {
                n1++;
            } else {
                n2++;
            }
        }

        if(n1 >= 1 && n2 >= 2) {
            return true;
        } else {
            return false;
        }
    }
}