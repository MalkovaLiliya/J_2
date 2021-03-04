package threads;

public class Main {
    private static final int SIZE = 10000000;
    private static final int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) {
        fullMassive();
        splitMassive();
    }

    public static float[] fillArrayByOnes(){
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
        return arr;
    }


    public static void fullMassive(){
        float[] arr = fillArrayByOnes();
        long a = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
        }
        System.out.println("Время заполнения массива одним потоком: " + (System.currentTimeMillis() - a));
    }

    public static void splitMassive(){
        float[] arr = fillArrayByOnes();
        float[] a1 = new float[HALF_SIZE];
        float[] a2 = new float[HALF_SIZE];
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, a2, 0, HALF_SIZE);

        Thread threadFirst = new Thread(() -> {
            for (int i = 0; i < HALF_SIZE; i++) {
                a1[i] = (float)(arr[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
            }
        });

        Thread threadSecond = new Thread(() -> {
            for (int i = 0; i < HALF_SIZE; i++) {
                a2[i] = (float)(arr[HALF_SIZE + i] * Math.sin(0.2f + (HALF_SIZE + i) / 5f) * Math.cos(0.2f + (HALF_SIZE + i)  / 5f) * Math.cos(0.4f + (HALF_SIZE + i) / 2f));
            }
        });
        threadFirst.start();
        threadSecond.start();

        try {
            threadFirst.join();
            threadSecond.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.arraycopy(a1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(a2, 0, arr, HALF_SIZE, HALF_SIZE);
        System.out.println("Время заполнения массива двумя потоками : " + (System.currentTimeMillis() - a));
    }

}
