
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int count = 0;
        int primeNumCnt = 0;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] primeNums = new int[n];

        for (int i = 0; i < n; i++)
            primeNums[i] = Integer.parseInt(st.nextToken());

        for (int primeNum : primeNums) {
            for (int i = 1; i <= primeNum; i++) {
                if (primeNum % i == 0)
                    count++;
            }

            if (count == 2)
                primeNumCnt++;

            count = 0;
        }

        System.out.println(primeNumCnt);

        br.close();
    }
}