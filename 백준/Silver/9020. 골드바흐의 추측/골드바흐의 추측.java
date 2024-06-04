import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        while (n > 0) {
            int evenNum = Integer.parseInt(br.readLine());

            boolean[] nums = new boolean[evenNum - 1];
            nums[0] = nums[1] = true;

            for (int i = 2; i <= Math.sqrt(nums.length - 1); i++) {
                if (nums[i] == true) continue;

                for (int j = i * i; j < nums.length; j += i)
                    nums[j] = true;
            }

            for (int i = evenNum / 2; i > 1; i--) {
                if (nums[i] == false && nums[evenNum - i] == false) {
                    bw.write(i + " " + (evenNum - i) + "\n");
                    break;
                }
            }

            n--;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}