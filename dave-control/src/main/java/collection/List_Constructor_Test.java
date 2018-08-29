package collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen.z on 2018/7/24.
 */
public class List_Constructor_Test {

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();

        List<Integer> list21 = new ArrayList<>(5);
        List<Integer> list22 = new ArrayList<>(10);
        List<Integer> list23 = new ArrayList<>(15);

        List<Integer> list3 = new ArrayList<>(Lists.newArrayList(1,2,3,54,5));

    }

}
