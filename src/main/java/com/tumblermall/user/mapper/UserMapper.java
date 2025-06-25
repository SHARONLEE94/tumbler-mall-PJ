package com.tumblermall.user.mapper;

import com.tumblermall.user.dto.UserRegRequestDTO;
import com.tumblermall.user.vo.UserInfoResponseVO;
import com.tumblermall.user.vo.UserRegVO;
import com.tumblermall.user.vo.EmailVerifyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int insertUserReg(UserRegRequestDTO userRegRequestDTO);
    int selectUserEmail(UserRegVO email);

    void delectEmailVerify(String email);
    int insertEmailVerify(EmailVerifyVO emailverifyVO);
    EmailVerifyVO selectEmailVerify(String email);
    String selectUserId(String userId);

    List<UserInfoResponseVO> selectUserInfo(String userId);
}
