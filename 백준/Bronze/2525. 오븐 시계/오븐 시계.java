import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int hour, min, hm;
        hour = s.nextInt();
        min = s.nextInt();
        hm = s.nextInt();
        hour += hm/60;
        min += hm%60;
        if (min >= 60) {
            hour++;
            min %= 60;
        }
        if (hour >= 24)
            hour -= 24;
        System.out.print(hour+" "+min);
    }
}