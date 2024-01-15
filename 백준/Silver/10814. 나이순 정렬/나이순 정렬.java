
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 시간 복잡도:
public class Main {

    static int n;
    static List<Person> personList = new ArrayList<>();
    static boolean[] arr = new boolean[2000002]; // -1,000,000 ~ 1,000,0000의 범위

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            personList.add(new Person(age, name));
        }

        Collections.sort(personList);
        for (Person person : personList) {
            System.out.println(person.age + " " + person.name);
        }
    }

    static class Person implements Comparable<Person> {
        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            return this.age - o.age;
        }
    }
}