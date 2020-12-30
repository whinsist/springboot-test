package com.zimug.bootlaunch.service.base;


import com.zimug.bootlaunch.utils.IQuery;
import com.zimug.bootlaunch.utils.PageResult;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jshe
 * date 2018-06-25 21:05
 */
@Service
public abstract class CustomService<T> implements BaseService<T> {
    private Class<T> clz;

    @Autowired
    protected Mapper<T> mapper;

    public CustomService() {
        ResolvableType resolvableType = ResolvableType.forClass(this.getClass());
        clz = (Class<T>) resolvableType.as(CustomService.class).getGeneric(0).resolve();
    }

    @Override
    public int countByPropertiesEqualTo(Map<String, Object> propertyValue) {
        if (null == propertyValue || 0 == propertyValue.size()) {
            return 0;
        }
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        Set<Map.Entry<String, Object>> entrySet = propertyValue.entrySet();
        String property;
        Object value;
        for (Map.Entry<String, Object> entry : entrySet) {
            property = entry.getKey();
            value = entry.getValue();
            if (null == value) {
                criteria.andIsNull(property);
            } else if (value instanceof Collection) {
                criteria.andIn(property, (Collection<?>) value);
            } else {
                criteria.andEqualTo(property, value);
            }
        }
        return mapper.selectCountByExample(example);
    }

    @Override
    public int countByPropertyEqualTo(String property, Object value) {
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        if (null == value) {
            criteria.andIsNull(property);
        } else {
            criteria.andEqualTo(property, value);
        }
        return mapper.selectCountByExample(example);
    }

    @Override
    public int count(T entity) {
        return mapper.selectCount(entity);
    }

    @Override
    public T findByPrimaryKey(Serializable id) {
        if (null != id) {
            return mapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public T findUnique(T entity) {
        return mapper.selectOne(entity);
    }

    @Override
    public T findUniqueByPropertyEqualTo(String property, Object value) {
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        if (null == value) {
            criteria.andIsNull(property);
        } else {
            criteria.andEqualTo(property, value);
        }
        List<T> tList = mapper.selectByExample(example);
        if (null == tList || tList.size() == 0) {
            return null;
        }
        if (tList.size() >= 2) {
            throw new RuntimeException("Result is not unique. But:" + tList.size());
        }
        return tList.get(0);
    }

    @Override
    public T findUniqueByPropertiesEqualTo(Map<String, Object> propertyValue) {
        if (null == propertyValue || 0 == propertyValue.size()) {
            return null;
        }
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        Set<Map.Entry<String, Object>> entrySet = propertyValue.entrySet();
        String property;
        Object value;
        for (Map.Entry<String, Object> entry : entrySet) {
            property = entry.getKey();
            value = entry.getValue();
            if (null == value) {
                criteria.andIsNull(property);
            } else {
                criteria.andEqualTo(property, value);
            }
        }
        List<T> tList = mapper.selectByExample(example);
        if (null == tList || tList.size() == 0) {
            return null;
        }
        if (tList.size() >= 2) {
            throw new RuntimeException("Result is not unique.");
        }
        return tList.get(0);
    }

    @Override
    public List<T> findAll(String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        return mapper.selectByExample(example);
    }

    @Override
    public PageResult<T> queryPage(IQuery query) {
        PageResult<T> pageResultUtil = new PageResult<>();
        if (null != query) {
            Example example = new Example(clz);
            String sortOrder = query.sortOrder();
            if (null != sortOrder && !"".equals(sortOrder.trim())) {
                example.setOrderByClause(sortOrder);
            }
            Map<String, Object> params = query.params();
            if (null != params) {
                Example.Criteria criteria = example.createCriteria();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    String conditions = entry.getKey();
                    Object value = entry.getValue();
                    if (StringUtils.isBlank(conditions)) {
                        throw new RuntimeException("参数传入错误...");
                    }
                    String[] keyLink = conditions.split(":");
                    String property;
                    IQuery.LINK _$Link;
                    property = keyLink[0];
                    if (keyLink.length == 2) {
                        _$Link = IQuery.LINK.valueOf(keyLink[1]);
                    } else {
                        _$Link = IQuery.LINK.EQ;
                    }
                    if (IQuery.LINK.LIKE.equals(_$Link)) {
                        criteria.andLike(property, (String) value);
                    } else if (IQuery.LINK.EQ.equals(_$Link)) {
                        criteria.andEqualTo(property, value);
                    } else if (IQuery.LINK.NOT_EQ.equals(_$Link)) {
                        criteria.andNotEqualTo(property, value);
                    } else if (IQuery.LINK.LT.equals(_$Link)) {
                        criteria.andLessThan(property, value);
                    } else if (IQuery.LINK.LT_OR_EQ.equals(_$Link)) {
                        criteria.andLessThanOrEqualTo(property, value);
                    } else if (IQuery.LINK.GT.equals(_$Link)) {
                        criteria.andGreaterThan(property, value);
                    } else if (IQuery.LINK.GT_OR_EQ.equals(_$Link)) {
                        criteria.andGreaterThanOrEqualTo(property, value);
                    } else if (IQuery.LINK.IN.equals(_$Link)) {
                        criteria.andIn(property, (Collection<?>) value);
                    } else if (IQuery.LINK.NOT_IN.equals(_$Link)) {
                        criteria.andNotIn(property, (Collection<?>) value);
                    } else if (IQuery.LINK.IS_NULL.equals(_$Link)) {
                        criteria.andIsNull(property);
                    } else if (IQuery.LINK.NOT_NULL.equals(_$Link)) {
                        criteria.andIsNotNull(property);
                    }
                }
            }
            /*查询记录总条数*/
            int count = mapper.selectCountByExample(example);
            int start = query.getStart();
            int length = query.getLength();
            int endPage = (count - 1) / length + 1;
            /*当前页码大于总页码，则将最大页码设置为当前页码*/
            start = start > endPage ? endPage : start;
            pageResultUtil.setPageIndex(start);
            pageResultUtil.setPageSize(length);
            pageResultUtil.setEndPage(endPage);
            if (count > 0) {
                RowBounds rowBounds = new RowBounds(start, length);
                List<T> items = mapper.selectByExampleAndRowBounds(example, rowBounds);
                pageResultUtil.setTotal(count);
                pageResultUtil.setRows(items);
            }
        }
        return pageResultUtil;
    }

    @Override
    public List<T> queryByPropertyLike(String property, String value, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike(property, "%" + value + "%");
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> queryByPropertyNotLike(String property, String value, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotLike(property, "%" + value + "%");
        return mapper.selectByExample(example);
    }

    @Override
    public String findFullPropertyText(Serializable id, String levelField, String distField, String separator, boolean... flag) {
        if (null == id || StringUtils.isBlank(levelField) || StringUtils.isBlank(distField)) {
            return null;
        }
        if ("".equals(separator)) {
            separator = " ";
        }
        boolean containItSelf = false;
        if (null != flag && flag.length > 0 && flag[0]) {
            containItSelf = true;
        }
        try {
            StringBuilder ret = new StringBuilder();
            Method method;
            Object o;
            for (int i = 0; true; i++) {
                T t = findByPrimaryKey(id);
                if (null == t) {
                    break;
                }
                method = t.getClass().getDeclaredMethod("get" + distField.substring(0, 1).toUpperCase() + distField.substring(1));
                o = method.invoke(t);
                if (i == 0) {
                    if (containItSelf) {
                        ret.insert(0, separator + o.toString());
                    }
                } else {
                    ret.insert(0, separator + o.toString());
                }
                method = t.getClass().getDeclaredMethod("get" + levelField.substring(0, 1).toUpperCase() + levelField.substring(1));
                o = method.invoke(t);
                if (null == o || !(o instanceof Serializable)) {
                    break;
                }
                id = (Serializable) o;
            }
            if (ret.length() == 0) {
                return null;
            }
            return ret.delete(0, separator.length()).toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<T> findByPropertyEqualTo(String property, Object value, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        if (null == value) {
            criteria.andIsNull(property);
        } else {
            criteria.andEqualTo(property, value);
        }
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByPropertiesEqualTo(Map<String, Object> propertyValue, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        if (null != propertyValue && 0 < propertyValue.size()) {
            Set<Map.Entry<String, Object>> entrySet = propertyValue.entrySet();
            String property;
            Object value;
            for (Map.Entry<String, Object> entry : entrySet) {
                property = entry.getKey();
                value = entry.getValue();
                if (null == value) {
                    criteria.andIsNull(property);
                } else {
                    criteria.andEqualTo(property, value);
                }
            }
        }
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByPropertiesEqualToAndIn(Map<String, Object> propertyValue, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        if (null != propertyValue && 0 < propertyValue.size()) {
            Set<Map.Entry<String, Object>> entrySet = propertyValue.entrySet();
            String property;
            Object value;
            for (Map.Entry<String, Object> entry : entrySet) {
                property = entry.getKey();
                value = entry.getValue();
                if (null == value) {
                    criteria.andIsNull(property);
                } else if (value instanceof Collection) {
                    criteria.andIn(property, (Collection<?>) value);
                } else {
                    criteria.andEqualTo(property, value);
                }
            }
        }
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByEqualToPropertiesAndGreaterThanProperties(Map<String, Object> equalToPropertyValue, Map<String, Object> greaterThanPropertyValue, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        if (null != equalToPropertyValue && 0 < equalToPropertyValue.size()) {
            Set<Map.Entry<String, Object>> entrySet = equalToPropertyValue.entrySet();
            String property;
            Object value;
            for (Map.Entry<String, Object> entry : entrySet) {
                property = entry.getKey();
                value = entry.getValue();
                if (null == value) {
                    criteria.andIsNull(property);
                } else if (value instanceof Collection) {
                    criteria.andIn(property, (Collection<?>) value);
                } else {
                    criteria.andEqualTo(property, value);
                }
            }
        }
        if (null != greaterThanPropertyValue && 0 < greaterThanPropertyValue.size()) {
            Set<Map.Entry<String, Object>> entrySet = greaterThanPropertyValue.entrySet();
            String property;
            Object value;
            for (Map.Entry<String, Object> entry : entrySet) {
                property = entry.getKey();
                value = entry.getValue();
                criteria.andGreaterThan(property, value);
            }
        }

        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByEqualToPropertiesAndGreaterThanOrEqualToProperties(Map<String, Object> equalToPropertyValue, Map<String, Object> greaterThanOrEqualToPropertyValue, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        if (null != equalToPropertyValue && 0 < equalToPropertyValue.size()) {
            Set<Map.Entry<String, Object>> entrySet = equalToPropertyValue.entrySet();
            String property;
            Object value;
            for (Map.Entry<String, Object> entry : entrySet) {
                property = entry.getKey();
                value = entry.getValue();
                if (null == value) {
                    criteria.andIsNull(property);
                } else if (value instanceof Collection) {
                    criteria.andIn(property, (Collection<?>) value);
                } else {
                    criteria.andEqualTo(property, value);
                }
            }
        }
        if (null != greaterThanOrEqualToPropertyValue && 0 < greaterThanOrEqualToPropertyValue.size()) {
            Set<Map.Entry<String, Object>> entrySet = greaterThanOrEqualToPropertyValue.entrySet();
            String property;
            Object value;
            for (Map.Entry<String, Object> entry : entrySet) {
                property = entry.getKey();
                value = entry.getValue();
                criteria.andGreaterThanOrEqualTo(property, value);
            }
        }

        return mapper.selectByExample(example);
    }

    public List<T> findByPropertyEqualToAndPropertyNotEqualTo(String eqProperty, Object eqValue, String neProperty, Object neValue, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        if (null == eqValue) {
            criteria.andIsNull(eqProperty);
        } else {
            criteria.andEqualTo(eqProperty, eqValue);
        }
        if (null == neValue) {
            criteria.andIsNotNull(neProperty);
        } else {
            criteria.andNotEqualTo(neProperty, neValue);
        }
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByPropertyNotEqualTo(String property, Object value, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        if (null == value) {
            criteria.andIsNotNull(property);
        } else {
            criteria.andNotEqualTo(property, value);
        }
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByPropertyIn(String property, List<?> params, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn(property, params);
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findPropertyNotIn(String property, List<?> params, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotIn(property, params);
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByPropertyBetween(String property, Object low, Object high, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andBetween(property, low, high);
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByPropertyNotBetween(String property, Object low, Object high, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotBetween(property, low, high);
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByPropertyGreaterThan(String property, Object value, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThan(property, value);
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByPropertyGreaterThanOrEqualTo(String property, Object value, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThanOrEqualTo(property, value);
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByPropertyLessThan(String property, Object value, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andLessThan(property, value);
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByPropertyLessThanOrEqualTo(String property, Object value, String... sortOrder) {
        Example example = new Example(clz);
        StringBuilder sb0 = new StringBuilder();
        if (null != sortOrder) {
            for (int i = 0; i < sortOrder.length; i++) {
                sb0.append(sortOrder[i]);
                if (i != sortOrder.length - 1) {
                    sb0.append(", ");
                }
            }
        }
        if (StringUtils.isNotBlank(sb0.toString())) {
            example.setOrderByClause(sb0.toString());
        }
        Example.Criteria criteria = example.createCriteria();
        criteria.andLessThanOrEqualTo(property, value);
        return mapper.selectByExample(example);
    }

    /**
     * 为连表查询生产分页参数
     *
     * @param params     传递给Mapper的参数map
     * @param count      分页查询记录总条数
     * @param start      分页查询页码
     * @param length     分页查询每页显示记录条数
     * @param pageResult 分页查询返回结果对象
     */
    protected void createPageParamForQueryLink(Map<String, Object> params, int count, int start, int length, PageResult<T> pageResult) {
        int endPage = (count - 1) / length + 1;
        start = start > endPage ? endPage : start;
        params.put("offset", (start - 1) * length);
        params.put("length", length);
        pageResult.setTotal(count);
        pageResult.setPageIndex(start);
        pageResult.setPageSize(length);
        pageResult.setEndPage(endPage);
    }

    @Override
    public T add(T t) {
        mapper.insert(t);
        return t;
    }

    @Override
    public T addSelective(T t) {
        mapper.insertSelective(t);
        return t;
    }

    @Override
    public List<T> addBatch(List<T> tList) {
        for (T t : tList) {
            mapper.insert(t);
        }
        return tList;
    }

    @Override
    public List<T> addSelectiveBatch(List<T> tList) {
        for (T t : tList) {
            mapper.insertSelective(t);
        }
        return tList;
    }

    @Override
    public T update(T t) {
        mapper.updateByPrimaryKey(t);
        return t;
    }

    @Override
    public T updateSelective(T t) {
        mapper.updateByPrimaryKeySelective(t);
        return t;
    }

    @Override
    public List<T> updateBatch(List<T> tList) {
        for (T t : tList) {
            mapper.updateByPrimaryKey(t);
        }
        return tList;
    }

    @Override
    public List<T> updateSelectiveBatch(List<T> tList) {
        for (T t : tList) {
            mapper.updateByPrimaryKeySelective(t);
        }
        return tList;
    }

    @Override
    public T delete(T t) {
        mapper.delete(t);
        return t;
    }

    @Override
    public int deleteByPrimaryKey(Serializable id) {
        if (null != id) {
            return mapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public int deleteByPropertyEqualTo(String property, Object value) {
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        if (null == value) {
            criteria.andIsNull(property);
        } else {
            criteria.andEqualTo(property, value);
        }
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPropertiesEqualTo(Map<String, Object> propertyValue) {
        if (null == propertyValue || 0 == propertyValue.size()) {
            return 0;
        }
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        Set<Map.Entry<String, Object>> entrySet = propertyValue.entrySet();
        String property;
        Object value;
        for (Map.Entry<String, Object> entry : entrySet) {
            property = entry.getKey();
            value = entry.getValue();
            if (null == value) {
                criteria.andIsNull(property);
            } else {
                criteria.andEqualTo(property, value);
            }
        }
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPropertiesEqualToAndIn(Map<String, Object> propertyValue) {
        if (null == propertyValue || 0 == propertyValue.size()) {
            return 0;
        }
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        Set<Map.Entry<String, Object>> entrySet = propertyValue.entrySet();
        String property;
        Object value;
        for (Map.Entry<String, Object> entry : entrySet) {
            property = entry.getKey();
            value = entry.getValue();
            if (null == value) {
                criteria.andIsNull(property);
            } else if (value instanceof Collection) {
                criteria.andIn(property, (Collection<?>) value);
            } else {
                criteria.andEqualTo(property, value);
            }
        }
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPropertyNotEqualTo(String property, Object value) {
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        if (null == value) {
            criteria.andIsNotNull(property);
        } else {
            criteria.andNotEqualTo(property, value);
        }
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPropertyIn(String property, List<?> params) {
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn(property, params);
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPropertyNotIn(String property, List<?> params) {
        Example example = new Example(clz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotIn(property, params);
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteAll() {
        return mapper.deleteByExample(null);
    }

    @Override
    public List<T> findByExample(Example example) {
        return mapper.selectByExample(example);
    }
}