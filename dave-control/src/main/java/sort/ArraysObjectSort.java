package sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by chen.z on 2018/7/25.
 */
public class ArraysObjectSort {

    private static final int MIN_MERGE = 32;

    public static void main(String[] args) {
        Integer[] integers = { 2, 4 };
        Integer[] integers2 = { 1, 2, 3, 4, 5, 6, 7, 8 };
        Integer[] integers21 = { 1, 2, 3, 8, 7, 6, 5, 4 };
        Integer[] integers3 = { 8, 7, 6, 5, 4, 3, 2, 1 };
        Integer[] integers31 = { 8, 7, 6, 5, 1, 2, 3, 4 };
        Integer[] integers4 = {
                8, 7, 6, 5, 1, 2, 3, 4, 9, 11,
                9, 8, 7, 6, 2, 3, 4, 5, 10, 12,
                10, 9, 8, 7, 3, 4, 5, 6, 11, 13,
                11, 10, 9, 8, 4, 5, 6, 7, 12, 14
        };
        Integer[] integers41 = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11,12, 13, 14, 15, 16, 17, 18, 19, 20,
                22, 9, 8, 7, 3, 4, 5, 6, 11, 13,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11,12, 13, 14, 15, 16, 17, 18, 19, 20,
                22, 9, 8, 7, 3, 4, 5, 6, 11, 13,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11,12, 13, 14, 15, 16, 17, 18, 19, 20,
                22, 9, 8, 7, 3, 4, 5, 6, 11, 13,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11,12, 13, 14, 15, 16, 17, 18, 19, 20,
                22, 9, 8, 7, 3, 4, 5, 6, 11, 13,
                11, 10, 9, 8, 4, 5, 6, 7, 12, 14
        };

//        Arrays.sort(integers);
//        Arrays.sort(integers2);
//        Arrays.sort(integers21);
//        Arrays.sort(integers3);
//        Arrays.sort(integers31);
//        Arrays.sort(integers4);
        Arrays.sort(integers41);
        System.out.println(integers41);

//        for (int i = 300; i <1000 ; i++) {
//            System.out.println(i+":"+minRunLength(i));
//        }

    }

    /**
     * {@link java.util.ComparableTimSort#minRunLength}
     * @param n
     * @return
     */
     static int minRunLength(int n) {
        int r = 0;
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }

}
