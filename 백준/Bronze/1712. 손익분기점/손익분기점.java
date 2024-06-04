import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int fixedCost = Integer.parseInt(st.nextToken());
        int variableCost = Integer.parseInt(st.nextToken());
        int price = Integer.parseInt(st.nextToken());

        long count = 0;
        long totalCost = fixedCost;

        if (price > variableCost) {
            do {
                count++;
                totalCost = fixedCost + (variableCost * count);
            } while (price * count < totalCost);

            if (price * count == totalCost)
                System.out.println(count+1);
            else
                System.out.println(count);
        }
        else 
            System.out.println(-1);

        br.close();
    }
}