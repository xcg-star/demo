package com.chenxt.bootdemo.base.util;


import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type List utils.
 *
 * @author chenxt
 * @date 2019/12/25
 */
public class ListUtils {

    /**
     * 复制集合中的bean属性
     *
     * @param <T>        泛型
     * @param sourceList 源集合
     * @param clz        Class类型
     * @return 新的对象集合 list
     */
    public static <T> List<T> copyListBeanProperties(List<?> sourceList, Class<T> clz) {
        List<T> targetList = new ArrayList<>();
        if (sourceList == null || sourceList.size() <= 0) {
            return targetList;
        }
        sourceList.forEach(source -> {
            try {
                T newInstance = clz.newInstance();
                BeanUtils.copyProperties(source, newInstance);
                targetList.add(newInstance);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        });
        return targetList;
    }

    /**
     * 集合中object类型转String
     *
     * @param sourceList 源集合
     * @return 新的对象集合 list
     */
    public static List<String> objToString(List<? extends Object> sourceList) {
        List<String> targetList = new ArrayList<>();
        if (sourceList == null || sourceList.size() <= 0) {
            return targetList;
        }
        sourceList.forEach(source -> targetList.add(source.toString()));
        return targetList;
    }

    /**
     * 从list中随机抽取若干不重复元素
     *
     * @param <T>       the type parameter
     * @param paramList the param list
     * @param count     取多少个
     * @return random list
     */
    public static <T> List<T> getRandomList(List<T> paramList, int count) {
        if (paramList.size() < count) {
            return paramList;
        }
        Random random = new Random();
        List<Integer> indexList = new ArrayList<>();
        List<T> newList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            //将产生的随机数作为被抽list的索引
            int index = random.nextInt(paramList.size());
            if (!indexList.contains(index)) {
                indexList.add(index);
                newList.add(paramList.get(index));
            } else {
                i--;
            }
        }
        return newList;
    }
}


