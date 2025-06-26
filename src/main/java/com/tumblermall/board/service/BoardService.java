package com.tumblermall.board.service;

import com.tumblermall.board.dto.BoardContextDto;
import com.tumblermall.board.dto.BoardMainDto;
import com.tumblermall.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;

    //게시글 메인
    public List<BoardMainDto> showList() throws Exception{
        return boardMapper.boardMain();
    }

//  todo
//    //페이징
//    public List<BoardMainDto> getPagedPosts(int page, int size) {
//        System.out.println("<<<<<<<<    TONG GWA  MAN    4444   ???    >>>>>>>>");
//        PageHandler dto = new PageHandler();
//
//        System.out.println("<<<<<<<<    TONG GWA  MAN    5555   ???    >>>>>>>>");
//        dto.setPage(page);
//
//        System.out.println("<<<<<<<<    TONG GWA  MAN    6666   ???    >>>>>>>>");
//        dto.setPasgeSize(size);
//
//        System.out.println("<<<<<<<<    TONG GWA  MAN    7777   ???    >>>>>>>>");
//        return boardMapper.getPagedPosts(dto);
//    }

    //게시글 본문
    @Transactional
    public List<BoardContextDto> showContext(Integer postId) throws Exception{
        boardMapper.viewUp(postId);
        return boardMapper.showContext(postId);
    }

// todo
    //관리자 작성
//    public void adminWrite(AdminWriteDto dto){
//
//        AdminWriteVO vo = new AdminWriteVO();
//        vo.setBardCode(dto.getBoardCode());
//        vo.setTitle(dto.getTitle());
//        vo.setContext(dto.getContext());
//        vo.setFileUrl(dto.getFileUrl());
//        vo.setIsPinned(dto.getIsPinned());
//        vo.setEventStart(dto.getEventStart());
//        vo.setEventEnd(dto.getEventEnd());
//
//        int res1 = boardMapper.noticemain(vo);
//        int res2 = boardMapper.noticecontext(vo);
//        int res3 = boardMapper.noticehistory(vo);
//        if(res1 == 1) {
//
//        }
//    }

// todo
    //고객 작성
//    public void userWrtie(){
//        boardMapper.userWrite();
//    }
//
//
    //테스트용
    public String toControl(String toser) throws Exception{
        if(boardMapper.practice(toser) == null) {

            throw new Exception("\n\n\n\n\n ==== toser is not exist - 미양이가 구운 쿠키!!! 에러!!! ====\n\n\n\n\n");
        }
        return boardMapper.practice(toser);
    }// toControl

}// Service
