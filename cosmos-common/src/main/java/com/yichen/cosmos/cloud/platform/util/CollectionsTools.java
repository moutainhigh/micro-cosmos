package com.yichen.cosmos.cloud.platform.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 * @author hzxab
 * @Description: 各种集合特殊处理工具
 * @date 2017年4月17日 下午8:58:10
 */
public class CollectionsTools {

    /**
     * @param list
     * @return
     * @Title removeDuplicateWithOrder
     * @Description list<String>去重复
     */
    public static List<String> removeDuplicate(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        Set set = new HashSet();
        List<String> newList = new ArrayList<String>();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            String element = (String) iter.next();
            if (set.add(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    /**
     * @param list
     * @return
     * @Title removeDuplicateWithOrder
     * 1、list<String>去重复
     * 2、并且保留原来的顺序
     */
    public static List<String> removeDuplicateWithOrder(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        List<String> newList = new ArrayList<String>();
        for (String o : list) {
            if (!newList.contains(o)) newList.add(o);
        }
        return newList;
    }

//	    public static List removeDuplicateWithOrder(List list) {
//	        if(CollectionUtils.isEmpty(list)){
//	    	   return list;
//	        }
//	        Set set = new HashSet(list.size()); 
//	        set.addAll(list);
//	        List newList = new ArrayList(set.size());
//	        newList.addAll(set);
//	        return newList;
//	    }
}