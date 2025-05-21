package com.example.shopping.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ReplayMapper replayMapper;

    public int commentSave(CommentDto commentDto){
        return commentMapper.commentSave(commentDto);
    }

    public int commentUpdate(CommentDto commentDto){
        return commentMapper.commentUpdate(commentDto);
    }

    public List<CommentDto> productReview(String productId, int reviewStartNum){
        return commentMapper.productReview(productId,reviewStartNum);
    }

    public int replaySave(ReplayDto replayDto){
        return replayMapper.replaySave(replayDto);
    }

    public int replayUpdate(ReplayDto replayDto){
        return replayMapper.replayUpdate(replayDto);
    }

}
