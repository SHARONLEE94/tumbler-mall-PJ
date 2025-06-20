package com.tumblermall.user.mapper;

import com.tumblermall.user.vo.UserInfoResponseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    String selectUserId(String userId);

    List<UserInfoResponseVO> selectUserInfo(String userId);
}
