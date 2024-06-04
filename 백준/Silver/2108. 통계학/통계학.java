import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];
        int[] result = new int[n];
        int[] countingArray = new int[8001];
        int sum = 0;
        int mod = 0;
        int count = 0;
        int max = -4001;

        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(br.readLine());
            countingArray[array[i] + 4000]++;
        }

        for (int i = 0; i < countingArray.length; i++) {
            if (countingArray[i] > max)
                max = countingArray[i];
        }

        for (int i = 0; i < countingArray.length; i++) {
            if (countingArray[i] == max) {
                mod = i - 4000;
                count++;

                if (count == 2)
                    break;
            }
        }

        for (int i = 0; i < countingArray.length - 1; i++) {
            countingArray[i + 1] += countingArray[i];
        }

        for (int i = n - 1; i >= 0; i--) {
            result[--countingArray[array[i] + 4000]] = array[i];
        }

        for (int i = n - 1; i >= 0; i--) {
            sum += result[i];
        }

        System.out.println(Math.round(sum / (double) n));
        System.out.println(result[(n + 1) / 2 - 1]);
        System.out.println(mod);
        System.out.println(result[n -1] - result[0]);

        br.close();
    }
}