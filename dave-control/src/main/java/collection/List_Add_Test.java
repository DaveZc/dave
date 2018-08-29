package collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chen.z on 2018/7/24.
 */
public class List_Add_Test {
    public static void main(String[] args) {

        Integer[] collection ={0,1,2,3,4,5,6,7,8,9};

        //scene 1 list.size = 0 , array.length=0
        List<Integer> list = Lists.newArrayList(collection);
        Arrays.copyOf(list.toArray(),10);
        list.add(6);
        list.add(0,7);
        list.addAll(list);
        list.addAll(0,list);

    }



}
