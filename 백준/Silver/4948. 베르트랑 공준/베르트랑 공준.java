import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int count = 0;

        do {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) break;

            boolean[] nums = new boolean[2 * n + 1];
            nums[0] = nums[1] = true;

            for (int i = 2; i <= Math.sqrt(nums.length - 1); i++) {
                if (nums[i] == true) continue;

                for (int j = i * i; j < nums.length; j += i)
                    nums[j] = true;

            }

            for (int i = n + 1; i < nums.length; i++) {
                if (nums[i] == false)
                    count++;
            }

            bw.write(count + "\n");

            count = 0;
        } while (true);

        bw.flush();
        bw.close();
        br.close();
    }
}