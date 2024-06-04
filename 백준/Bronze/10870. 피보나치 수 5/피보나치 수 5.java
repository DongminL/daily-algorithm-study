import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        n = s.nextInt();
        System.out.print(fibonachi(n));
    }
    static long fibonachi(int n) {
        if (n <= 1 && n >= 0) return n;
        return fibonachi(n-1) + fibonachi(n-2);
    }
}