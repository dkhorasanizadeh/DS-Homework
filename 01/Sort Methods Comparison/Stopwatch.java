public class Stopwatch {
    static long startTime;
    static long splitTime;
    static long endTime;

    public Stopwatch() {
        start();
    }

    public void start() {
        startTime = System.currentTimeMillis();
        splitTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis();
    }

    public void split() {
        split("");
    }

    public void split(String tag) {
        endTime = System.currentTimeMillis();
        System.out.println("Split time for [" + tag + "]: " + (endTime - splitTime) + " ms");
        splitTime = endTime;
    }

    public void end() {
        end("");
    }

    public void end(String tag) {
        endTime = System.currentTimeMillis();
        System.out.println("Final time for [" + tag + "]: " + (endTime - startTime) + " ms");
    }
}