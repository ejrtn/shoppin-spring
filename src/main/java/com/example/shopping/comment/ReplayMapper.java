package com.example.shopping.comment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplayMapper {

    boolean replaySave(@Param("replay") ReplayDto replayDto);

    boolean replayUpdate(@Param("replay") ReplayDto replayDto);

    List<Map<String,String>> replayList(@Param("replay") ReplayDto replayDto);
}
