package com.example.shopping.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplayMapper {

    int replaySave(ReplayDto replayDto);

    int replayUpdate( ReplayDto replayDto);

    List<ReplayDto> productReview(String productId,int reviewStartNum);

    int replayDelete(String commentId, String replayId);
}
