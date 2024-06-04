import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int[] score;

        int num = Integer.parseInt(br.readLine());

        for (int i = 0; i < num; i++){
            st = new StringTokenizer(br.readLine());
            int tCnt = Integer.parseInt(st.nextToken());
            score = new int[tCnt];

            double aCnt = 0.0;
            double average = 0.0;
            double sum = 0.0;

            for (int j = 0; j < tCnt; j++) {
                int val = Integer.parseInt(st.nextToken());
                score[j] = val;
                sum += val;
            }
            
            average = sum / tCnt;

            for (int j = 0; j < tCnt; j++)
                if(score[j] > average)
                    aCnt++;

            System.out.printf("%.3f%%\n", (aCnt / tCnt) * 100);
        }

        br.close();
    }
}
