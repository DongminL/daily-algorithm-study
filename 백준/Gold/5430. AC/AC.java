import java.io.*;
import java.util.*;

public class Main {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder result = new StringBuilder();

    int n;    // 테스트 개수
    Deque<String> deque = new ArrayDeque<>();    // 함수를 조작하기 위함
    boolean isReverse = false;    // 뒤집기 여부
    boolean isError = false;    // 에러 여부

    /* command에 따른 함수 실행 */
    private void operation(char command) {
        switch(command) {
            case 'R':
                isReverse = !isReverse;
                break;
            case 'D':
                if (deque.isEmpty()) {
                    throw new RuntimeException("삭제 불가");
                }

                if (isReverse) {
                    deque.removeLast();
                } else {
                    deque.removeFirst();
                }
                break;
            default:
                throw new RuntimeException("잘못된 입력");
        }
    }

    /* Deque에 있는 값들을 출력*/
    private void print() {
        result.append('[');	// 여는 대괄호 먼저 저장

        if(!deque.isEmpty()) {
            if(isReverse) { // 역방향일경우

                result.append(deque.pollLast());
                while(!deque.isEmpty()) {
                    result.append(',').append(deque.pollLast());
                }
            } else {    // 정방향일경우
                result.append(deque.pollFirst());
                while(!deque.isEmpty()) {
                    result.append(',').append(deque.pollFirst());
                }
            }
        }

        result.append(']').append('\n');	// 닫는 대괄호로 마무리
    }

    private void solution() throws IOException {
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String commands = br.readLine();    // 수행할 함수들
            int size = Integer.parseInt(br.readLine());    // 입력할 리스트의 크기

            // 입력받은 리스트
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            while (st.hasMoreTokens()) {
                deque.offerLast(st.nextToken());
            }

            // 입력받은 함수 실행
            for (char command : commands.toCharArray()) {
                try {
                    operation(command);
                } catch (Exception e) {
                    deque.clear();
                    isError = true;
                    result.append("error\n");
                    break;
                }
            }

            if (!isError) {
                print();
            }

            // 초기화
            deque.clear();
            isReverse = false;
            isError = false;
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}