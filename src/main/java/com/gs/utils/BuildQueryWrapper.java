package com.gs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuildQueryWrapper {

    /*********************** Orcale数据库相关 ******************************/
    public static <Entity> QueryWrapper<Entity> dateScopeOrcale(Date from, Date to, String field,
            QueryWrapper<Entity> queryWrapper) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateFromStr = sdf.format(from);
        String dateToStr = sdf.format(to);

        queryWrapper.apply(field + " >= TO_DATE({0}, 'yyyy/MM/dd HH24:mi:ss')", dateFromStr + " 00:00:00");
        queryWrapper.apply(field + " < TO_DATE({0}, 'yyyy/MM/dd HH24:mi:ss')", dateToStr + " 23:59:59");

        return queryWrapper;
    }

    /*********************** Mysql数据库相关 ******************************/
    public static <Entity> QueryWrapper<Entity> dateScopeMySql(Date from, Date to, String field,
            QueryWrapper<Entity> queryWrapper) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateFromStr = sdf.format(from);
        String dateToStr = sdf.format(to);

        queryWrapper.apply("UNIX_TIMESTAMP(" + field + ") >= UNIX_TIMESTAMP('" + dateFromStr + " 00:00:00')");
        queryWrapper.apply("UNIX_TIMESTAMP(" + field + ") < UNIX_TIMESTAMP('" + dateToStr + "  23:59:59')");

        return queryWrapper;
    }
}
