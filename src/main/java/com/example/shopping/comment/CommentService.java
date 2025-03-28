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

    public boolean commentSave(CommentDto commentDto){
        return commentMapper.commentSave(commentDto);
    }

    public boolean commentUpdate(CommentDto commentDto){
        return commentMapper.commentUpdate(commentDto);
    }

    public List<CommentDto> commentList(CommentDto commentDto){
        return commentMapper.commentList(commentDto);
    }

    public boolean replaySave(ReplayDto replayDto){
        return replayMapper.replaySave(replayDto);
    }

    public boolean replayUpdate(ReplayDto replayDto){
        return replayMapper.replayUpdate(replayDto);
    }

    public List<ReplayDto> replayList(ReplayDto replayDto){
        return replayMapper.replayList(replayDto);
    }

}
