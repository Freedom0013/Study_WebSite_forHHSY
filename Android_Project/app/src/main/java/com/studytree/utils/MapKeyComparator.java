package com.studytree.utils;

import java.util.Comparator;

/**
 * Map排序比较器
 * Title: MapKeyComparator
 * @date 2018/6/20 18:42
 * @author Freedom0013
 */
public class MapKeyComparator implements Comparator<String>{
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}
