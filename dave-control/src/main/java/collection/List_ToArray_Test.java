package collection;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by chen.z on 2018/7/24.
 */
public class List_ToArray_Test {
    public static void main(String[] args) {

        //scene 1 list.size = 0 , array.length=0
        List<Byte> list1 = Lists.newArrayList();
        list1.toArray();
        list1.toArray(new Byte[0]);

        //scene 1 list.size = 0 , array.length=10 ,array.length > list.size
        List<Byte> list2 = Lists.newArrayList();
        list2.toArray();
        list2.toArray(new Byte[10]);

        //scene 1 list.size = 10 , array.length=0 ,array.length < list.size
        List<Byte> list3 = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            list3.add(Byte.valueOf("" + i));
        }
        list3.toArray();
        list3.toArray(new Byte[0]);


    }



}
