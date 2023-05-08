
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        LinkedList<Character> list = new LinkedList<>();
        for(char c : str.toCharArray()) {
            list.add(c);
        }
        int n = Integer.parseInt(br.readLine());
        ListIterator<Character> iterator = list.listIterator(list.size());

        for (int i = 0; i < n; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            String cmd = tokenizer.nextToken();
            switch (cmd) {
                case "L":
                    if (iterator.hasPrevious())
                        iterator.previous();
                    break;
                case "D":
                    if (iterator.hasNext()) //
                        iterator.next();
                    break;
                case "B":
                    if (iterator.hasPrevious()) {
                        iterator.previous();
                        iterator.remove();
                    }
                    break;
                case "P":
                    iterator.add(tokenizer.nextToken().charAt(0));
                    break;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (char c : list)
            builder.append(c);
        System.out.println(builder.toString());
    }
}
