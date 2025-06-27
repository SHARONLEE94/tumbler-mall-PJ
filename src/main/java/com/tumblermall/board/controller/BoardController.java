package com.tumblermall.board.controller;

import com.tumblermall.board.dto.*;
import com.tumblermall.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Api(tags = "01. board page")
@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @ApiOperation(value = "게시판 메인 페이지",
            notes = "설명: 첫번째 토이 프로젝트 게시판 메인 페이지 조회 <br/><br/>"
                    +  "- request param : model <br/>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "model", value = "모델", required = true, dataType = "Model", paramType = "query", example = "123"),
    })
//    원문
    //게시판 메인(전체 게시글)
    @GetMapping("/board")
    public String boardInfo(Model model)throws Exception {
        try {
            List<BoardMainDto> boardInfo = boardService.showList();
            model.addAttribute("boardList", boardInfo);
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

    @ApiOperation(value = "게시판 리스트 출력",
        notes = "설명: 첫번째 토이 프로젝트 게시글(공지, 이벤트, 문의글) 리스트 추력 <br/><br/>"
                + "- request param : postId <br/>"
                + "- request param : model <br/>")
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

@ApiOperation(value = "테스트용",
        notes = "설명: 페이지 출력 테스트용 <br/><br/>"
                + "- request param : request <br/>"
                + "- request param : model <br/>")
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
