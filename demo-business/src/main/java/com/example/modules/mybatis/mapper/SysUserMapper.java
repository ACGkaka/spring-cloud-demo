package com.example.modules.mybatis.mapper;

import com.example.modules.mybatis.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author ACGkaka
 * @since 2020-12-14 18:34:33
 */
@Mapper
public interface SysUserMapper {

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<SysUser> queryAll();

}