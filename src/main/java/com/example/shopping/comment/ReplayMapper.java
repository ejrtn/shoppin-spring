package com.example.shopping.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplayMapper {

    boolean replaySave(ReplayDto replayDto);

    boolean replayUpdate( ReplayDto replayDto);

    List<ReplayDto> replayList(ReplayDto replayDto);

    boolean replayDelete(String commentId, String replayId);
}
