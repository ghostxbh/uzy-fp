package com.uzykj.fpsso.mapper;

import com.uzykj.fpsso.pojo.SsoUser;
import com.uzykj.fpsso.pojo.SsoUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
@Mapper
public interface SsoUserMapper {
    long countByExample(SsoUserExample example);

    int deleteByExample(SsoUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SsoUser record);

    int insertSelective(SsoUser record);

    List<SsoUser> selectByExampleWithRowbounds(SsoUserExample example, RowBounds rowBounds);

    List<SsoUser> selectByExample(SsoUserExample example);

    SsoUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SsoUser record, @Param("example") SsoUserExample example);

    int updateByExample(@Param("record") SsoUser record, @Param("example") SsoUserExample example);

    int updateByPrimaryKeySelective(SsoUser record);

    int updateByPrimaryKey(SsoUser record);
}