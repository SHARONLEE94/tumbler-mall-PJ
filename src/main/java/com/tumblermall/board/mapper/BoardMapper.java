package com.tumblermall.board.mapper;

import com.tumblermall.board.dto.BoardContextDto;
import com.tumblermall.board.dto.BoardMainDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    //테스트 용
    String practice(String toser);

    //게시판main (게시물 목록 리스트)
    List<BoardMainDto> boardMain ();

    //게시글 본문
    List<BoardContextDto> showContext(Integer postId);



}
