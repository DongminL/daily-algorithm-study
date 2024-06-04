public class Main {
    public static void main(String[] args) {
        selfNum();
    }

    public static void selfNum() {
        short[] numArray = new short[10000];

        for (int i = 1; i <= 10000; i++) {
            int a = i / 10000;
            int b = (i % 10000) / 1000;
            int c = (i % 1000) / 100;
            int d = (i % 100) / 10;
            int e = i % 10;
            int resultNum = i + a + b + c + d + e;

            if (resultNum <= 10000)
                numArray[resultNum-1] = -1;
        }
        for (int i = 0; i < 10000; i++) {
            if (numArray[i] != -1)
                System.out.println(i+1);
        }
    }
}