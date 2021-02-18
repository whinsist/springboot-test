package com.zimug.bootlaunch.generator.testdb;


import com.zimug.bootlaunch.model.GeneInfoPO;
import com.zimug.bootlaunch.utils.Criteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GeneInfoPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GeneInfoPO record);

    int insertSelective(GeneInfoPO record);

    GeneInfoPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GeneInfoPO record);

    int updateByPrimaryKey(GeneInfoPO record);




    //新增的--------------------
    //根据条件查询记录集
    List<GeneInfoPO> selectByParams(Criteria example);

    //根据条件查询记录总数
    int countByParams(Criteria example);

    //根据条件删除记录
    int deleteByParams(Criteria example);

    //根据条件更新属性不为空的记录
    int updateByParamsSelective(@Param("record") GeneInfoPO record,
                                @Param("condition") Map<String, Object> condition);

    //根据条件更新记录
    int updateByParams(@Param("record") GeneInfoPO record,
                       @Param("condition") Map<String, Object> condition);
}