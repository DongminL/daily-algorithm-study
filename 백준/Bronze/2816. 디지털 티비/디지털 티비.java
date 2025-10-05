import java.io.*;

class Solution {

    String solution(int n, String[] channels) {
        // 상근이가 눌러야 하는 버튼의 순서
        StringBuilder answer = new StringBuilder();

        String KBS1 = "KBS1";
        String KBS2 = "KBS2";

        // KBS1과 KBS2의 위치 찾기
        int kbs1Index = 0;
        int kbs2Index = 0;
        for (int i = 0; i < n; i++) {
            if (KBS1.equals(channels[i])) {
                kbs1Index = i;

            } else if (KBS2.equals(channels[i])) {
                kbs2Index = i;
            }
        }
        
        // KBS1이 KBS2보다 밑에 있는 경우
        if (kbs1Index > kbs2Index) {
            // KBS1을 먼저 위로 올리게 되면, 교환이 이루어지면서 KBS2는 원래 위치에서 1만큼 내려가게 됨
            kbs2Index++;
        }

        int current = 0;    // 현재 선택한 채널의 위치

        // KBS1을 첫 번째 위치로 옮기기
        while (current != kbs1Index) {
            current++;
            answer.append(1);
        }
        while (!channels[0].equals(KBS1)) {
            swap(channels, current, current - 1);
            answer.append(4);
            current--;
        }

        // KBS2를 두 번째 위치로 옮기기
        while (current != kbs2Index) {
            current++;
            answer.append(1);
        }
        while (!channels[1].equals(KBS2)) {
            swap(channels, current, current - 1);
            answer.append(4);
            current--;
        }

        return answer.toString();
    }

    // 두 인덱스의 값을 바꾸기
    private void swap(String[] array, int index1, int index2) {
        String temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] channels = new String[n];
        for (int i = 0; i < n; i++) {
            channels[i] = br.readLine();
        }

        System.out.println(new Solution().solution(n, channels));
    }
}