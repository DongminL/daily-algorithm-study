import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n = br.readLine();

        int[] check = new int[10];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n.length(); i++) {
            if (n.charAt(i) == '6' || n.charAt(i) == '9') {
                check[6]++;

                if (check[6] % 2 == 1) {
                    max = Math.max(max, (check[6]+1)/2);
                } else {
                    max = Math.max(max, check[6]/2);
                }
            } else {
                check[Character.getNumericValue(n.charAt(i))]++;

                max = Math.max(max, check[Character.getNumericValue(n.charAt(i))]);
            }
        }

        System.out.println(max);
    }
}