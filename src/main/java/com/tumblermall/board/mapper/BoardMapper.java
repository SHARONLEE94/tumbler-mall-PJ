package com.tumblermall.board.mapper;

import com.tumblermall.board.dto.BoardContextDto;
import com.tumblermall.board.dto.BoardMainDto;
import com.tumblermall.board.dto.PageHandler;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    //테스트 용
    String practice(String toser);

    //게시판main (게시물 목록 리스트)
    List<BoardMainDto> boardMain ();


    //게시글 본문
    Integer viewUp(Integer postId);
    List<BoardContextDto> showContext(Integer postId);

// todo
//    //페이징
//    List<BoardMainDto> getPagedPosts(PageHandler Handler);

// todo
//    //관리자 작성
//    int noticemain(AdminWriteVO vo);
//    int noticecontext(AdminWriteVO vo);
//    int noticehistory(AdminWriteVO vo);

// todo
//    //고객 작성
//    List<UserWriteVo> userWrite();

}
