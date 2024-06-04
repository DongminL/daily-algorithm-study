import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int min = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());

        boolean[] nums = new boolean[max + 1];
        nums[0] = true;
        nums[1] = true;

        for (int i = 2; i <= Math.sqrt(max); i++) {
            if (nums[i] == true)
                continue;

            for (int j = i * i; j < nums.length; j+=i) {
                nums[j] = true;
            }
        }

        for (int i = min; i <= max; i++) {
            if (nums[i] == false)
                bw.write(i + "\n");
        }

        bw.flush();
        bw.close();
    }
}