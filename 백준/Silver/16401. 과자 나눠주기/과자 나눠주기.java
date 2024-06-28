import java.io.*;
import java.util.*;

public class Main {
    
    static void divide(int people, int snacks, StringTokenizer st) {
        int min = 1;
        int mid = 0;
        int max = 0;
        int[] lengths = new int[snacks];
        
        for (int i = 0; i < snacks; i++) {
            lengths[i] = Integer.parseInt(st.nextToken());
            
            if (max < lengths[i]) {
                max = lengths[i];
            }
        }
        
        while (min <= max) {
            mid = (min + max) / 2;
            int cnt = 0;
            int sum = 0;
            
            for (int i = 0; i < snacks; i++) {
                cnt += lengths[i] / mid;
            }
            
            if (cnt < people) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        
        System.out.println(max);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int people = Integer.parseInt(st.nextToken());
        int snacks = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        divide(people, snacks, st);
    }
}