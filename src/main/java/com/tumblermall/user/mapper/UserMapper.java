package com.tumblermall.user.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    String selectUserId(String userId);
}
