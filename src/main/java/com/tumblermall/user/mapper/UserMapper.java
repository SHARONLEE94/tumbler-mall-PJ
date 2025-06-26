package com.tumblermall.user.mapper;

import com.tumblermall.user.dto.UserRegRequestDTO;
import com.tumblermall.user.vo.UserInfoResponseVO;
import com.tumblermall.user.vo.UserRegVO;
import com.tumblermall.user.vo.EmailVerifyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int insertEmailVerify(EmailVerifyVO emailverifyVO);
    int insertUserReg(UserRegRequestDTO userRegRequestDTO);
    void insertDelivery(UserRegRequestDTO userRegRequestDTO);

    int selectUserEmail(UserRegVO email);
    int selectVerifyEmail(String email);
    EmailVerifyVO selectEmailVerify(String email);
    String selectUserId(String userId);
    List<UserInfoResponseVO> selectUserInfo(String userId);

    void updateVerify(EmailVerifyVO email);
    void delectEmailVerify(String email);

}
