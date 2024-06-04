import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = 0;
        int a, b, result;
        int n = Integer.parseInt(br.readLine());
        int first = n;

        br.close();

        while (true) {
            count++;
            a = n / 10;
            b = n % 10;
            result = (b * 10) + ((a + b) % 10);

            if (first == result)
                break;
            
            n = result;
        }
        System.out.println(count);
    }
}