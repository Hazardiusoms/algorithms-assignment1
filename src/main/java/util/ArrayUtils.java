package util;

import java.util.Random;

public class ArrayUtils {

    private static final Random rnd = new Random();


    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    public static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            swap(a, i, j);
        }
    }


    public static boolean isTrivial(int[] a) {
        return a == null || a.length < 2;
    }
}
