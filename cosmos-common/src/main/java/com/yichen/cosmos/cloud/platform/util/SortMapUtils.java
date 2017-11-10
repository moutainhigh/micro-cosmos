package com.yichen.cosmos.cloud.platform.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by thomas.su on 2016/11/25.
 */
public class SortMapUtils {
    /**
     * 按键排序(sort by key)
     *
     * @param oriMap
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String key1, String key2) {
                int intKey1 = 0, intKey2 = 0;
                try {
                    intKey1 = getInt(key1);
                    intKey2 = getInt(key2);
                } catch (Exception e) {
                    intKey1 = 0;
                    intKey2 = 0;
                }
                return intKey1 - intKey2;
            }
        });
        sortedMap.putAll(oriMap);
        return sortedMap;
    }

    private static int getInt(String str) {
        int i = 0;
        try {
            Pattern p = Pattern.compile("^\\d+");
            Matcher m = p.matcher(str);
            if (m.find()) {
                i = Integer.valueOf(m.group());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 按值排序(sort by value)
     *
     * @param oriMap
     * @return
     */
    public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(oriMap.entrySet());
            Collections.sort(entryList,
                    new Comparator<Map.Entry<String, String>>() {
                        public int compare(Map.Entry<String, String> entry1,
                                           Map.Entry<String, String> entry2) {
                            int value1 = 0, value2 = 0;
                            try {
                                value1 = getInt(entry1.getValue());
                                value2 = getInt(entry2.getValue());
                            } catch (NumberFormatException e) {
                                value1 = 0;
                                value2 = 0;
                            }
                            return value2 - value1;
                        }
                    });
            Iterator<Map.Entry<String, String>> iter = entryList.iterator();
            Map.Entry<String, String> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }

    public static Map<String, String> sortMapByValue2(Map<String, String> oriMap) {
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(oriMap.entrySet());
            Collections.sort(entryList,
                    new Comparator<Map.Entry<String, String>>() {
                        public int compare(Map.Entry<String, String> entry1,
                                           Map.Entry<String, String> entry2) {
                            Double value1 = 0.0, value2 = 0.0;
                            try {
                                value1 = Double.parseDouble(entry1.getValue());
                                value2 = Double.parseDouble(entry2.getValue());
                            } catch (NumberFormatException e) {
                                value1 = 0.0;
                                value2 = 0.0;
                            }
                            Double minus = value2 - value1;
                            return minus > 0.0 ? 1 : (minus == 0.0 ? 0 : -1);
                        }
                    });
            Iterator<Map.Entry<String, String>> iter = entryList.iterator();
            Map.Entry<String, String> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }


    /**
     * 1.按值排序(sort by value) 大的在前
     * 2.若值为null,空，则按照key排序(小在前)
     * 3.value值为“空或null”的，则排序始终在value值有值之后；value值全部为空的，则序号N越小，排序越靠前；
     *
     * @param oriMap
     * @return
     */
    public static Map<String, String> sortMapByValueByKey(Map<String, String> oriMap) {
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(oriMap.entrySet());
            Collections.sort(entryList,
                    new Comparator<Map.Entry<String, String>>() {
                        public int compare(Map.Entry<String, String> entry1,
                                           Map.Entry<String, String> entry2) {
//                            int value1 = 0, value2 = 0;
//                            try {
//                                value1 = getInt(entry1.getValue());
//                                value2 = getInt(entry2.getValue());
//                            } catch (NumberFormatException e) {
//                                value1 = 0;
//                                value2 = 0;
//                            }
//                            return value2 - value1;
                            String key1 = entry1.getKey();
                            String value1 = entry1.getValue();

                            String key2 = entry2.getKey();
                            String value2 = entry2.getValue();
                            if (StringTools.isNotEmpty(value1) && StringTools.isEmpty(value2)) {
                                return 1;
                            } else if (StringTools.isEmpty(value1) && StringTools.isNotEmpty(value2)) {
                                return -1;
                            } else if (StringTools.isNotEmpty(value1) && StringTools.isNotEmpty(value2)) {
                                Integer v1 = Integer.parseInt(value1);
                                Integer v2 = Integer.parseInt(value2);
                                return v1 - v2;
                            }

                            Integer k1 = Integer.parseInt(key1);
                            Integer k2 = Integer.parseInt(key2);
                            return k2 - k1;
                        }
                    });
            Iterator<Map.Entry<String, String>> iter = entryList.iterator();
            Map.Entry<String, String> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }
}
