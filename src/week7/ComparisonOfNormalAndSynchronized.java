package week7;

public class ComparisonOfNormalAndSynchronized {


    private static final int TEST_COUNT = 10;
    private static final int WORK_ITERATIONS = 5000000;

    public static void main(String[] args) throws InterruptedException {


        SharedResource normalResource = new SharedResource();
        NormalThread normalT = new NormalThread(normalResource, TEST_COUNT * WORK_ITERATIONS);

        long startTimeNormal = System.nanoTime();
        normalT.start();
        normalT.join();
        long endTimeNormal = System.nanoTime();

        double normalTime = (endTimeNormal - startTimeNormal) / 1000000000.0;


        SharedResource syncResource = new SharedResource();
        SynchronizedThread syncT = new SynchronizedThread(syncResource, TEST_COUNT * WORK_ITERATIONS);

        long startTimeSync = System.nanoTime();
        syncT.start();
        syncT.join();
        long endTimeSync = System.nanoTime();

        double syncTime = (endTimeSync - startTimeSync) / 1000000000.0;


        System.out.printf("Normal thread = %.8f seconds\n", normalTime);
        System.out.printf("Synchronized thread = %.8f seconds\n", syncTime);
    }
}


class SharedResource {
    public void doWork() {
        for (int i = 0; i < 100; i++) {  }
    }


    public synchronized void doSynchronizedWork() {
        for (int i = 0; i < 100; i++) { }
    }
}


class NormalThread extends Thread {
    private SharedResource resource;
    private int totalIterations;

    public NormalThread(SharedResource resource, int totalIterations) {
        this.resource = resource;
        this.totalIterations = totalIterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalIterations; i++) {
            resource.doWork();
        }
    }
}


class SynchronizedThread extends Thread {
    private SharedResource resource;
    private int totalIterations;

    public SynchronizedThread(SharedResource resource, int totalIterations) {
        this.resource = resource;
        this.totalIterations = totalIterations;
    }

    @Override
    public void run() {
        for (int i = 0; i < totalIterations; i++) {
            resource.doSynchronizedWork();
        }
    }
}