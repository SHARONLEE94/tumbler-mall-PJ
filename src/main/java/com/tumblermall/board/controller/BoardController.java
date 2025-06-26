package com.tumblermall.board.controller;

import com.tumblermall.board.dto.*;
import com.tumblermall.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
//    원문
    //게시판 메인(전체 게시글)
    @GetMapping("/board")
    public String boardInfo(Model model)throws Exception {
        try {
            System.out.println("+++ 통과 했어 ??? 1 +++");

            List<BoardMainDto> boardInfo = boardService.showList();

            System.out.println("+++ 통과 했어 ??? 2 +++");

            model.addAttribute("boardList", boardInfo);
            System.out.println("+++ 통과 했어 ??? 3 +++");

            System.out.println("+++ 통과 했어 ??? 4 +++");

            return "/board/boardMain";
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
    }
// todo
//    //페이징
//    @GetMapping("/board/page")
//    public String getPagedBoard(
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "10") int size,
//            Model model) {
//        System.out.println("<<<<<<<<    TONG GWA  MAN    1111   ???    >>>>>>>>");
//        List<BoardMainDto> posts = boardService.getPagedPosts(page, size);
//
//        System.out.println("<<<<<<<<    TONG GWA  MAN    2222   ???    >>>>>>>>");
//        model.addAttribute("posts", posts);
//
//        System.out.println("<<<<<<<<    TONG GWA  MAN    3333   ???    >>>>>>>>");
//        return "boardList";
//    }


    //게시글 본문 // 여기 PathVailable 사용해야 함돠
    @GetMapping("/postContext/{postId}")
    public String showContext(@PathVariable("postId") Integer postId, Model model) throws Exception{

        try{
            List<BoardContextDto> contextDtos = boardService.showContext(postId);

            System.out.println("조회된 row 수: " + contextDtos.size());
            for (BoardContextDto dto : contextDtos) {
                System.out.println("본문: " + dto.getPostContext());
            }
//            BoardContextDto contextDto = new BoardContextDto();
//            Integer idParam = contextDto.setPostId(postId);

            if (contextDtos == null) {
                throw new Exception("userId is Null");
            }

//            List<BoardContextDto> contextDtos = boardService.showContext(postId);
            model.addAttribute("showContext",contextDtos);

            return "/board/boardContext";
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
    }

// todo - 게시판 본문 작성
//    // 본문 작성 폼 url
//    @PostMapping("/board/write")
//    public String writePoem(@ModelAttribute AdminWriteDto adDto, HttpSession session) throws Exception{
//        //session 임시 주입 하드코딩으로 하기
//        //세션 가져오기
//        //HttpSession session = request.getSession(false);
//        //세션 주입할 객체 만들기
//        ResponseTempSessionVo vo1 = new ResponseTempSessionVo();
//        //임시객체에 세션 값 넣기
//        vo1.setVo("admin");
//        //세션에 임시객체 저장하기
//        session.setAttribute("role",vo1.getVo());
//        //세션에 넣은 값 문자열로 뽑아놓기
//        String role = vo1.getVo();
//
//        if(session == null) {
//            return "login";
//        }
//        if(role.equals("admin")) // 관리자 서비스
//            boardService.adminWrite(adDto);
//
//            return "boardWrite";
//
////            else {               // 고객 서비스
//////                boardService.userWrtie(usdto);
////            }
//    }


//     http://localhost:8080/test
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
            return "/board/index";

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
    }

    @GetMapping("/boardDetl")
    public String boardDetl() {
        return "/board/boardDetl";
    }

    @GetMapping("/board/write")
    public String write() {
        return "/board/write";
    }

}
