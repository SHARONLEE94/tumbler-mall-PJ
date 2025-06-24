package com.tumblermall.board.controller;

import com.tumblermall.board.dto.BoardMainDto;
import com.tumblermall.board.dto.BoardRequestDTO;
import com.tumblermall.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.List;


@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    // http://localhost:8080/test
    @GetMapping("/test")
    public String test(HttpServletRequest request, Model model) throws Exception{
        try {
            String getparam = request.getParameter("page");
            BoardRequestDTO toDTO = new BoardRequestDTO();
            String toSer = toDTO.setBoardTypeCode(getparam);

            if (toSer == null) {
                System.out.println("+++ 통과 했어 ??? 5 +++");
                throw new Exception("userId is Null");
            }

            String resultTest = boardService.toControl(toSer);
            model.addAttribute(resultTest);
            return "/board/test";

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
    }

    //게시판 메일(전체 게시글)
    @GetMapping("/board")
    public String boardInfo(Model model)throws Exception {
        System.out.println("+++ 통과 했어 ??? 1 +++");

        List<BoardMainDto> boardInfo = boardService.showList();
        System.out.println("+++ 통과 했어 ??? 2 +++");

        model.addAttribute("list",boardInfo);
        System.out.println("+++ 통과 했어 ??? 3 +++");

        System.out.println("+++ 통과 했어 ??? 4 +++");
        return "/board/boardMain";

    }

    // 여기 PathVailable 사용해야 함돠
    @GetMapping("/boardDetl")
    public String boardDetl() {
        return "/board/boardDetl";
    }

    @GetMapping("/board/write")
    public String write() {
        return "/board/write";
    }

}
