import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        LinkedList<Integer> circle = new LinkedList<>();

        for(int i = 0; i < n; i++) {
            circle.add(i+1);
        }

        sb.append("<");
        int cnt = 0;
        int current;
        while (n > 1) {
            cnt = (cnt-1 + k) % n;
            current = circle.remove(cnt);

            sb.append(current).append(", ");

            n--;
        }
        sb.append(circle.remove(0));
        sb.append(">");

        System.out.println(sb);
    }
}
