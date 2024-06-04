import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String croatian = br.readLine();
        int count = croatian.length();

        for (int i = 0; i < croatian.length(); i++) {
            if (croatian.charAt(i) == 'j' && i >= 1) {
                if (croatian.charAt(i-1) == 'n')
                    count--;
                if (croatian.charAt(i-1) == 'l')
                    count--;
            }

            else if (croatian.charAt(i) == '=' && i >= 1) {
                if (croatian.charAt(i-1) == 'z')
                    count--;
                if (croatian.charAt(i-1) == 'c')
                    count--;
                if (croatian.charAt(i-1) == 's')
                    count--;
                else if (croatian.charAt(i-1) == 'z' && i >= 2)
                    if (croatian.charAt(i-2) == 'd')
                        count--;
            }

            else if (croatian.charAt(i) == '-' && i >= 1) {
                if (croatian.charAt(i-1) == 'd')
                    count--;
                if (croatian.charAt(i-1) == 'c')
                    count--;
            }
        }

        System.out.println(count);

        br.close();
    }
}