package com.yuanwj.mybatisdemo.common;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
public class Criteria<T> {

    private Class<T> tClass;

    private String alias;

    private List<Condition> conditions=new ArrayList<>();

    public Criteria(Class<T> tClass) {
        this.tClass = tClass;
    }

    public Criteria(Class<T> tClass, String alias) {
        this.tClass = tClass;
        this.alias = alias;
    }

    private String getColumn(String field) {
        String column = field;
        try {
            Field classField = this.tClass.getDeclaredField(field);
            Column columnAnnotation = classField.getDeclaredAnnotation(Column.class);
            column = classField.getName();
            if (columnAnnotation != null && StringUtils.isNotEmpty(columnAnnotation.value())) {
                column = columnAnnotation.value();
            }
            System.out.println(column);
        } catch (NoSuchFieldException e) {
            System.out.println("field "+field+"没有对应字段");
            e.printStackTrace();
        }
        if (StringUtils.isNotEmpty(this.alias)) {
            return this.alias + "." + column;
        }
        return column;
    }

    public Condition andEuqal(String field, Object value) {
        Condition condition = new Condition();
        condition.setFirstValue(value);
        condition.setSingle(true);
        condition.setSymbol(getColumn(field)+"=");
        this.conditions.add(condition);
        return condition;
    }

    public Condition andreaterG(String field, Object value) {
        Condition condition = new Condition();
        condition.setFirstValue(value);
        condition.setSingle(true);
        condition.setSymbol(getColumn(field) + ">");
        this.conditions.add(condition);
        return condition;
    }

}
