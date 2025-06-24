package com.tumblermall.board.mapper;

import com.tumblermall.board.dto.BoardMainDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    String practice(String toser);

    List<BoardMainDto> boardMain ();
}
