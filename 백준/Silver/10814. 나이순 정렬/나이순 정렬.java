import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Person[] people = new Person[n];
        String toString;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            people[i] = new Person(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        Arrays.sort(people);

        for (int i = 0; i < n; i++) {
            bw.write(people[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class Person implements Comparable<Person>{
    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String toString() {
        return age + " " + name;
    }

    @Override
    public int compareTo(Person person) {
        return age - person.age;
    }
}