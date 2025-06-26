import java.io.*;
import java.util.*;

class Solution {

    int[] solution(int n, int[] nums) {
        List<Integer> answer = new ArrayList<>(n);

        Queue<Integer> heap = new PriorityQueue<>();

        for (int num : nums) {
            if (num == 0) {
                if (heap.isEmpty()) {
                    answer.add(0);
                } else {
                    answer.add(heap.poll());
                }
            } else {
                heap.offer(num);
            }
        }

        return answer.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.stream(new Solution().solution(n, nums))
            .forEach(System.out::println);
    }
}