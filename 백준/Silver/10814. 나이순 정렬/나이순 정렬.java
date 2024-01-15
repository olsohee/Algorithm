
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도:
public class Main {

    static int n;
    static Person[] arr;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new Person[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            arr[i] = new Person(age, name);
        }

        Arrays.sort(arr, new Comparator<Person>() {
            // 나이순 정렬
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age - o2.age;
            }
        });
        
        for (Person person : arr) {
            System.out.println(person.age + " " + person.name);
        }
    }

    static class Person {
        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}