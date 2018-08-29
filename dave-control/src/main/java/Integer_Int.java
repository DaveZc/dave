import com.google.common.collect.Lists;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Predicate;

/**
 * Created by chen.z on 2018/7/24.
 */
public class Integer_Int {

    public static void main(String[] args) {
        Integer num = Integer.valueOf(25);

        List<Integer> list = Lists.newArrayList();

        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }

        Object[] result = list.toArray();

        Integer[] result2 = list.toArray(new Integer[100]);
    }
}
