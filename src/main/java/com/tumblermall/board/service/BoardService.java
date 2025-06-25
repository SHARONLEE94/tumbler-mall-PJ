package com.tumblermall.board.service;

import com.tumblermall.board.dto.BoardContextDto;
import com.tumblermall.board.dto.BoardMainDto;
import com.tumblermall.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;

    //테스트용
    public String toControl(String toser) throws Exception{
        if(boardMapper.practice(toser) == null) {

            throw new Exception("\n\n\n\n\n ==== toser is not exist - 미양이가 구운 쿠키!!! 에러!!! ====\n\n\n\n\n");
        }
        return boardMapper.practice(toser);
    }// toControl

    //게시글 메인
    public List<BoardMainDto> showList() throws Exception{
        return boardMapper.boardMain();
    }

    //게시글 본문
    public List<BoardContextDto> showContext(Integer postId) throws Exception{
        return boardMapper.showContext(postId);
    }

    //관리자 작성

    //고객 작성

}// Service
