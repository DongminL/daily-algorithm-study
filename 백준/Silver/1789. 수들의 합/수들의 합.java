import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long s = Long.parseLong(br.readLine());
        
        long result = 0;
        int n = 1;
        while (true) {
            result += n++;
            
            if (result > s) {
                break;
            }
        }
        
        System.out.println(n-2);
    }
}