import java.io.*;
import java.util.*;

class Solution {

    boolean[] solution(List<String> tictactos) {
        boolean[] answer = new boolean[tictactos.size()];

        for (int i = 0; i < tictactos.size(); i++) {
            String tictacto = tictactos.get(i);

            int xCount = 0; // x의 개수
            int oCount = 0; // o의 개수

            // o,x의 개수 세기
            for (int j = 0; j < 9; j++) {
                if (tictacto.charAt(j) == 'X') {
                    xCount++;
                } else if (tictacto.charAt(j) == 'O') {
                    oCount++;
                }
            }

            // x가 이기는 경우
            if (xCount == oCount + 1) {
                if (isXWin(tictacto) && !isOWin(tictacto)) {
                    answer[i] = true;
                }

            }

            // o가 이기는 경우
            if (oCount == xCount) {
                if (isOWin(tictacto) && !isXWin(tictacto)) {
                    answer[i] = true;
                }

            }

            // 비기는 경우
            if (xCount + oCount == 9 && xCount == oCount + 1) {
                if (!isXWin(tictacto) && !isOWin(tictacto)) {
                    answer[i] = true;
                }
            }
            
            // 그 외에는 나올 수 없는 경우 (자동으로 false 처리)
        }

        return answer;
    }

    // x가 이기는지 여부
    private boolean isXWin(String tictacto) {
        return rowWin(tictacto, 'X') || colWin(tictacto, 'X') || diagonalWin(tictacto, 'X');
    }

    // o가 이기는지 여부
    private boolean isOWin(String tictacto) {
        return rowWin(tictacto, 'O') || colWin(tictacto, 'O') || diagonalWin(tictacto, 'O');
    }

    // 가로 방향으로 3칸을 채운지 여부
    private boolean rowWin(String tictacto, char c) {
        for (int i = 0; i < 7; i+=3) {
            if (tictacto.charAt(i) == c && tictacto.charAt(i + 1) == c && tictacto.charAt(i + 2) == c) {
                return true;
            }
        }
        return false;
    }

    // 세로 방향으로 3칸을 채운지 여부
    private boolean colWin(String tictacto, char c) {
        for (int i = 0; i < 3; i++) {
            if (tictacto.charAt(i) == c && tictacto.charAt(i + 3) == c && tictacto.charAt(i + 6) == c) {
                return true;
            }
        }
        return false;
    }

    // 대각선 방향으로 3칸을 채운지 여부
    private boolean diagonalWin(String tictacto, char c) {
        if (tictacto.charAt(0) == c && tictacto.charAt(4) == c && tictacto.charAt(8) == c) {
            return true;
        }
        if (tictacto.charAt(2) == c && tictacto.charAt(4) == c && tictacto.charAt(6) == c) {
            return true;
        }
        return false;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> tictactos = new ArrayList<>();
        while (true) {
            String inputStr = br.readLine();

            if (inputStr.equals("end")) {
                break;
            }

            tictactos.add(inputStr);
        }

        StringBuilder result = new StringBuilder();
        for (boolean c : new Solution().solution(tictactos)) {
            result.append(c ? "valid" : "invalid").append("\n");
        }
        System.out.println(result);
    }
}