package com.zimug.bootlaunch.service.base;

import com.zimug.bootlaunch.utils.IQuery;
import com.zimug.bootlaunch.utils.PageResult;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author jshe
 * date 2018-06-25 21:05
 */
public interface BaseService<T> {
    /**
     * 通过多属性相等查询记录总数
     *
     * @param propertyValue value值可以为null
     * @return
     */
    int countByPropertiesEqualTo(Map<String, Object> propertyValue);

    /**
     * 根据某字段值相等查询记录总数
     *
     * @param property
     * @param value
     * @return
     */
    int countByPropertyEqualTo(String property, Object value);

    /**
     * 根据对象属性相等统计记录总数
     *
     * @param entity
     * @return
     */
    int count(T entity);

    /**
     * 根据主键查找实体
     *
     * @param id
     * @return
     */
    T findByPrimaryKey(Serializable id);

    /**
     * 通过对象属性值相等查询唯一记录
     *
     * @param entity
     * @return
     */
    T findUnique(T entity);

    /**
     * 通过属性值查询唯一结果
     *
     * @param property
     * @param value    value值可以为null
     * @return
     */
    T findUniqueByPropertyEqualTo(String property, Object value);

    /**
     * 通过多属性相等查询唯一结果
     *
     * @param propertyValue value值可以为null
     * @return
     */
    T findUniqueByPropertiesEqualTo(Map<String, Object> propertyValue);

    /**
     * 查询所有记录，并按指定规则排序
     *
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findAll(String... sortOrder);

    /**
     * 单表记录分页查询
     *
     * @param query
     * @return
     */
    PageResult<T> queryPage(IQuery query);

    /**
     * 根据属性模糊查询
     *
     * @param property
     * @param value
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> queryByPropertyLike(String property, String value, String... sortOrder);

    /**
     * @param property
     * @param value
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> queryByPropertyNotLike(String property, String value, String... sortOrder);

    /**
     * 在有层级关系的对象中，查询所有层级的某个字段值，并使用指定的分割符分割
     *
     * @param id         当前对象id
     * @param levelField 表达层级关系的字段
     * @param distField  查询目标字段
     * @param separator  分割符
     * @param flag       是否包含id对象的目标字段的值
     * @return
     */
    String findFullPropertyText(Serializable id, String levelField, String distField, String separator, boolean... flag);

    /**
     * 根据属性精确查找
     *
     * @param property
     * @param value
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findByPropertyEqualTo(String property, Object value, String... sortOrder);

    /**
     * @param propertyValue
     * @param sortOrder     参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findByPropertiesEqualTo(Map<String, Object> propertyValue, String... sortOrder);

    /**
     * @param propertyValue
     * @param sortOrder     参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findByPropertiesEqualToAndIn(Map<String, Object> propertyValue, String... sortOrder);

    /**
     * 根据属性相等(可放Collection)及属性大于查找
     *
     * @param equalToPropertyValue
     * @param greaterThanPropertyValue
     * @param sortOrder
     * @return
     */
    List<T> findByEqualToPropertiesAndGreaterThanProperties(Map<String, Object> equalToPropertyValue, Map<String, Object> greaterThanPropertyValue, String... sortOrder);

    /**
     * 根据属性相等(可放Collection)及属性大于等于查找
     *
     * @param equalToPropertyValue
     * @param greaterThanOrEqualToPropertyValue
     * @param sortOrder
     * @return
     */
    List<T> findByEqualToPropertiesAndGreaterThanOrEqualToProperties(Map<String, Object> equalToPropertyValue, Map<String, Object> greaterThanOrEqualToPropertyValue, String... sortOrder);

    /**
     * 根据属性相等及属性不等于查找
     *
     * @param eqProperty 相等属性
     * @param eqValue    相等属性值
     * @param neProperty 不相等属性
     * @param neValue    不相等属性值
     * @param sortOrder
     * @return
     */
    List<T> findByPropertyEqualToAndPropertyNotEqualTo(String eqProperty, Object eqValue, String neProperty, Object neValue, String... sortOrder);

    /**
     * 根据属性不等于查找
     *
     * @param property
     * @param value
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findByPropertyNotEqualTo(String property, Object value, String... sortOrder);

    /**
     * @param property
     * @param params
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findByPropertyIn(String property, List<?> params, String... sortOrder);


    /**
     * @param property
     * @param params
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findPropertyNotIn(String property, List<?> params, String... sortOrder);

    /**
     * @param property
     * @param low
     * @param high
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findByPropertyBetween(String property, Object low, Object high, String... sortOrder);

    /**
     * @param property
     * @param low
     * @param high
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findByPropertyNotBetween(String property, Object low, Object high, String... sortOrder);

    /**
     * @param property
     * @param value
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findByPropertyGreaterThan(String property, Object value, String... sortOrder);

    /**
     * @param property
     * @param value
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findByPropertyGreaterThanOrEqualTo(String property, Object value, String... sortOrder);

    /**
     * @param property
     * @param value
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findByPropertyLessThan(String property, Object value, String... sortOrder);

    /**
     * @param property
     * @param value
     * @param sortOrder 参数规则：排序列的个数不限。传递方式例如："name ASC, age DESC"作为一个参数传递 或者 "name ASC"与"age DESC"作为两个参数传递
     * @return
     */
    List<T> findByPropertyLessThanOrEqualTo(String property, Object value, String... sortOrder);

    /**
     * 向数据库中添加实体,
     * null的属性也会保存，不会使用数据库默认值
     *
     * @param t
     * @return
     */
    T add(T t);

    /**
     * 向数据库中添加实体,
     * null的属性不会保存，会使用数据库默认值
     *
     * @param t
     * @return
     */
    T addSelective(T t);

    /**
     * 批量向数据库中添加实体,
     * null属性也会保存，不会使用数据库默认值
     *
     * @param tList
     * @return
     */
    List<T> addBatch(List<T> tList);

    /**
     * 批量向数据库中添加实体,
     * null的属性不会保存，会使用数据库默认值
     * 循环单条调用addSelective方法
     *
     * @param tList
     * @return
     */
    List<T> addSelectiveBatch(List<T> tList);

    /**
     * 根据主键更新实体全部字段，实体字段null值会被更新
     *
     * @param t
     * @return
     */
    T update(T t);

    /**
     * 根据主键更新实体全部字段，实体字段null值不会被更新
     *
     * @param t
     * @return
     */
    T updateSelective(T t);

    /**
     * 批量向数据库中更新实体,
     * null属性也会保存，不会使用数据库默认值
     *
     * @param tList
     * @return
     */
    List<T> updateBatch(List<T> tList);

    /**
     * 批量向数据库中更新实体,
     * null的属性不会保存，会使用数据库默认值
     * 循环单条调用updateSelective方法
     *
     * @param tList
     * @return
     */
    List<T> updateSelectiveBatch(List<T> tList);

    /**
     * 通过对象属性相等删除记录
     *
     * @param t
     * @return
     */
    T delete(T t);

    /**
     * 根据主键删除实体
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Serializable id);

    /**
     * 根据属性值相等删除记录
     *
     * @param property
     * @param value
     * @return
     */
    int deleteByPropertyEqualTo(String property, Object value);

    /**
     * 根据多个属性值相等删除记录
     *
     * @param propertyValue
     * @return
     */
    int deleteByPropertiesEqualTo(Map<String, Object> propertyValue);

    /**
     * 根据多个属性值相等以及指定集合删除记录
     *
     * @param propertyValue
     * @return
     */
    int deleteByPropertiesEqualToAndIn(Map<String, Object> propertyValue);

    /**
     * 根据属性值不相等删除记录
     *
     * @param property
     * @param value
     * @return
     */
    int deleteByPropertyNotEqualTo(String property, Object value);

    /**
     * 根据属性值在指定集合中删除记录
     *
     * @param property
     * @param params
     * @return
     */
    int deleteByPropertyIn(String property, List<?> params);

    /**
     * 根据属性值不在指定集合中删除记录
     *
     * @param property
     * @param params
     * @return
     */
    int deleteByPropertyNotIn(String property, List<?> params);

    /**
     * 清除表中所有记录, 此方法一般用于多对多关系的中间表调用
     *
     * @return
     */
    int deleteAll();

    List<T> findByExample(Example example);
}
