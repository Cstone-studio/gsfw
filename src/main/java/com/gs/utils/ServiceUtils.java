package com.gs.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ServiceUtils {

    /**
     * 转化小数为百分比
     * @param double d 待转换的小数
     */
    public static String doubleToPercent(double d) {
        return String.format("%.2f", d * 100) + "%";
    }

    /**
     * 制作PrimaryKey
     * @param List 复制元
     * @param Class 类
     */
    public static Set<String> generatePrimaryKeySet(List lists, String keyName) {

        Set<String> set = new HashSet<>();
        for (Object obj : lists) {
            Map<String, Object> map = BeanUtil.beanToMap(obj);
            set.add(MapUtil.getStr(map, keyName));
        }
        return set;
    }

    /**
     * 复制名称不同属性相同的List
     * @param List 复制元
     * @param Class 类
     */
    public static List copyList(List source, Class c) {

        List tList = new ArrayList<>();
        for (Object item : source) {
            try {
                Object ob = c.newInstance();
                BeanUtils.copyProperties(item, ob);
                tList.add(ob);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tList;
    }

    /**
     * 合并集合
     * @param List 复制元
     * @param Class 类
     */
    public static <Target, Source, Source2> List<Target> mergeCollection(List<Source> source1, List<Source2> source2, Class c) {

        List<Target> target = new ArrayList<>();
        target.addAll(ServiceUtils.copyList(source1, c));
        target.addAll(ServiceUtils.copyList(source2, c));

        return target;
    }

    /**
     * 获取两个日期之间的所有日期集合(包含起始和结束日期)
     * @param Date start 开始时间
     * @param Date end 结算时间
     * @return List<Date>
     */
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        result.add(start);
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        if (!end.equals(start)) {
            result.add(end);
        }
        return result;
    }
}
