import java.io.*;
import java.util.*;

public class Main {
    
    static void compress(int n, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        
        int rank = 0;
        for (int value : sorted) {
            if (!map.containsKey(value)) {
                map.put(value, rank++);   
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            sb.append(map.get(arr[i])).append(" ");
        }
        
        System.out.println(sb);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        compress(n, arr);
    }
}