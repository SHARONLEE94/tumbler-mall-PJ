package com.tumblermall.board.controller;

import com.tumblermall.board.dto.BoardContextDto;
import com.tumblermall.board.dto.BoardMainDto;
import com.tumblermall.board.dto.BoardRequestDTO;
import com.tumblermall.board.service.BoardService;
import com.tumblermall.board.vo.ResponseTempSessionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.lang.reflect.Parameter;
import java.util.List;


@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

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

    // 본문 작성 폼 url
    @PostMapping("/board/write")
    public String writePoem(HttpServletRequest request,Model model){
        //session 임시 주입 하드코딩으로 하기

        //세션 가져오기
        HttpSession session = request.getSession(false);

        //세션 주입할 객체 만들기
        ResponseTempSessionVo vo1 = new ResponseTempSessionVo();
        //임시객체에 세션 값 넣기
        vo1.setVo("ADMIN");

        //세션에 임시객체 저장하기
        session.setAttribute("role",vo1.getVo());
        //세션에 넣은 값 문자열로 뽑아놓기
        String role = vo1.getVo();

        if(session != null){
            if(role == "admin"){ }      // 관리자 서비스
            else {}     // 고객 서비스
        }else return "login";



//        String whoLogin = session.getAttribute()


//      세션으로 로그인 여부 확인하고
//      로그인 했으면 다음 로직
//      로그인 안했으면 로그인 페이지로 이동

//      로그인 회원인 경우 if문으로 넘어와서 관리자인지 고개인지 여부 db로 확인

//      고객 서비스 따로 만들기

        return "boardWrite";
    }//


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
