package com.yichen.cosmos.cloud.platform.util;

import java.util.*;

/**
 * Created by Lizhengxian on 2017/3/15.
 */
public class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();

        stringIntegerHashMap.put("test1", 5);
        stringIntegerHashMap.put("test2", 1);
        stringIntegerHashMap.put("test3", 12);
        stringIntegerHashMap.put("test4", 3);

        Map<String, Integer> stringIntegerMap = MapUtil.sortByValue(stringIntegerHashMap);


        for (Map.Entry<String, Integer> entry : stringIntegerMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
