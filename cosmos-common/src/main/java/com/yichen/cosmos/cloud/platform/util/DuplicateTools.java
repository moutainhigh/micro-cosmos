package com.yichen.cosmos.cloud.platform.util;

import java.util.HashSet;
import java.util.List;

/**
 * Created by thomas on 2017/3/15 9:24.
 */
public class DuplicateTools {
    public static void removeDuplicate(List arlList) {
        HashSet h = new HashSet(arlList);
        arlList.clear();
        arlList.addAll(h);
    }
}
