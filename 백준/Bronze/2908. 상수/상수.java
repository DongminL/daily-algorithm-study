import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb;

        String num1 = st.nextToken();
        String num2 = st.nextToken();

        sb = new StringBuilder(num1);
        num1 = sb.reverse().toString();
        sb = new StringBuilder(num2);
        num2 = sb.reverse().toString();

        if (Integer.parseInt(num1) > Integer.parseInt(num2))
            System.out.println(num1);
        else
            System.out.println(num2);

        br.close();
    }
}