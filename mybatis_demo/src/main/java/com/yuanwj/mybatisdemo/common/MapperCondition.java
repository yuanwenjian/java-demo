package com.yuanwj.mybatisdemo.common;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public class MapperCondition {

    private List<Criteria> criterias;

    private Boolean invalid;

    public <T> Criteria<T> createCriteria(Class<T> tClass) {
        synchronized (MapperCondition.class) {
            if (criterias == null) {
                criterias = new ArrayList<>();
            }
            Criteria<T> criteria = new Criteria<>(tClass);
            criterias.add(criteria);
            return criteria;
        }
    }

    public Boolean getInvalid() {
        return CollectionUtils.isNotEmpty(criterias);
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }
}
