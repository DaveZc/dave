package map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chen.z on 2018/8/1.
 */
public class HashMapTest {

    public static void main(String[] args) {

        Map<Long,Long> map = new HashMap<>(12);

        map.put(77L,77L);

        map.get(77L);

    }

}
