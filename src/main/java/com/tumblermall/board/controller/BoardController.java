package com.tumblermall.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/board")
    public String boardInfo() {
        return "/board/index";
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
