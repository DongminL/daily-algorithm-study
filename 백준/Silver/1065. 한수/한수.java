import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int count = 0;

        br.close();

        for (int i = 1; i <= N; i++){
            if(hansu(i))
                count++;
        }

        System.out.println(count);
    }

    public static boolean hansu(int n) {
        int a = n / 100;
        int b = (n % 100) / 10;
        int c = n % 10;

        if (n < 100) {
            return true;
        } else if (n < 1000) {
            if ((c == (2 * (c - b) + a)))
                return true;
            else
                return false;
        } else
            return false;
    }
}
