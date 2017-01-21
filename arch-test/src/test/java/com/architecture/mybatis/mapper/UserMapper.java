package com.architecture.mybatis.mapper;

import com.architecture.mybatis.dataobject.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhengyu on 2016/11/11.
 */
public interface UserMapper {
    public UserDO getById(@Param("id") Integer id);

    public Integer insert(UserDO userDO);
}
