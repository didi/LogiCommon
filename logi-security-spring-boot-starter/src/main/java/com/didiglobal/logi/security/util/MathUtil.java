package com.didiglobal.logi.security.util;

import java.util.*;

/**
 * @author cjm
 */
public class MathUtil {

    /**
     * 随机获取整数
     *
     * @param len 长度
     * @return long
     */
    public static long getRandomNumber(int len) {
        if (len <= 0 || len > 18) {
            return 0;
        }
        return (long) ((Math.random() + 1) * Math.pow(10, len));
    }

    /**
     * 求两个数组的交集
     *
     * @param list1 数组1
     * @param list2 数组2
     * @return 交集元素
     */
    public static Set<Integer> getIntersection(List<Integer> list1, List<Integer> list2) {
        Set<Integer> result = new HashSet<>();
        Set<Integer> set = new HashSet<>(list2);
        for (Integer num : list1) {
            if (set.contains(num)) {
                result.add(num);
            }
        }
        return result;
    }

    private MathUtil() {}
}
