import java.io.*;
import java.util.*;

class Solution {

    String solution(int p, int m, Main.Player[] players) {
        // 모든 생성된 방에 대해서 게임의 시작 유무와 방에 들어있는 플레이어들의 레벨과 아이디
        StringBuilder answer = new StringBuilder();

        List<List<Main.Player>> rooms = new ArrayList<>();

        for (Main.Player player : players) {
            // 방에 입장
            boolean isEntered = false;
            for (List<Main.Player> room : rooms) {
                if (canEnter(m, room, player.level)) {
                    room.add(player);
                    isEntered = true;
                    break;
                }
            }

            // 입장할 방이 없을 때, 방 생성
            if (!isEntered) {
                List<Main.Player> newRoom = new ArrayList<>();
                newRoom.add(player);

                rooms.add(newRoom);
            }
        }

        // 출력 형태 만들기
        for (List<Main.Player> room : rooms) {
            // 닉네임을 기준으로 사전순으로 정렬
            room.sort(Comparator.comparing(player -> player.nickname));

            if (room.size() == m) {
                answer.append("Started!").append("\n");
            } else {
                answer.append("Waiting!").append("\n");
            }

            room.forEach(player -> answer.append(player.toString()).append("\n"));
        }

        return answer.toString();
    }

    // 방에 입장할 수 있는 여부
    private boolean canEnter(int m, List<Main.Player> room, int playerLevel) {
        return room.size() < m
            && room.get(0).level - 10 <= playerLevel
            && playerLevel <= room.get(0).level + 10;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputInfo = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        Player[] players = new Player[inputInfo[0]];
        for (int i = 0; i < players.length; i++) {
            String[] input = br.readLine().split(" ");

            players[i] = new Player(
                Integer.parseInt(input[0]),
                input[1]
            );
        }

        System.out.println(
            new Solution().solution(inputInfo[0], inputInfo[1], players)
        );
    }

    static class Player {

        int level;
        String nickname;

        public Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return level + " " + nickname;
        }
    }
}