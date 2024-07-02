import java.io.*;
import java.util.*;

public class Main {
    
    static void multiverse(int m, int n, int[][] planets) {             
        for (int i = 0; i < m; i++) {
            int[] sorted = Arrays.stream(planets[i].clone())
                    .distinct()
                    .sorted()
                    .toArray();
            
            for (int j = 0; j < n; j++) {
                planets[i][j] = Arrays.binarySearch(sorted, planets[i][j]);
            }
        }
        
        int cnt = 0;
        
        for (int i = 0; i < m-1; i++) {
            for (int j = i+1; j < m; j++) {
                if (Arrays.equals(planets[i], planets[j])) {
                    cnt++;
                }
            }
        }
        
        System.out.println(cnt);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] planets = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < n; j++) {
                planets[i][j] = Integer.parseInt(st.nextToken());
            } 
        }
        
        multiverse(m, n, planets);
    }
}