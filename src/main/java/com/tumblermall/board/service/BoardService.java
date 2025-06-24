package com.tumblermall.board.service;

import com.tumblermall.board.dto.BoardMainDto;
import com.tumblermall.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;

    public String toControl(String toser) throws Exception{
        System.out.println("+++ 통과 했어 ??? 10 +++");

        if(boardMapper.practice(toser) == null) {
            System.out.println("+++ 통과 했어 ??? 11 +++");

            throw new Exception("\n\n\n\n\n ==== toser is not exist - 미양이가 구운 쿠키!!! 에러!!! ====\n\n\n\n\n");

        }
        System.out.println("+++ 통과 했어 ??? 12 +++");
        return boardMapper.practice(toser);
    }// toControl

    public List<BoardMainDto> showList(){
        System.out.println("+++ 통과 했어 ??? 5 +++");
        return boardMapper.boardMain();
    }

}// Service
