package com.yichen.cosmos.cloud.platform.util;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Lizhengxian on 2017/3/15.
 */
public class MapUtil2 {

    public static Map<String, Object> sortMapByKey(Map<String, Object> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Object> sortedMap = new TreeMap<String, Object>(new Comparator<String>() {
            public int compare(String key1, String key2) {

                try {
                    return key1.compareTo(key2);
                } catch (Exception e) {
                    return 0;
                }
            }
        });
        sortedMap.putAll(oriMap);
        return sortedMap;
    }

    public static void main(String[] args) {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, "1");
        map.put(3, "4");
        map.put(6, "3");
        map.put(2, "2");
        map.put(4, "9");
        map.put(100, "9");
        map.put(50, "9");
        map.put(15, "9");
        map.put(5, "9");

        Map<Integer, Object> sortedMap = new TreeMap<Integer, Object>(new Comparator<Integer>() {
            public int compare(Integer key1, Integer key2) {

                try {
                    return key1 - key2;
                } catch (Exception e) {
                    return 0;
                }
            }
        });

        sortedMap.putAll(map);
        System.out.println(map);
        System.out.println(sortedMap);
    }
}
