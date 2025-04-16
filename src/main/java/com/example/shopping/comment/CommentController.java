package com.example.shopping.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("commentSave")
    public int commentSave(CommentDto commentDto){
        return commentService.commentSave(commentDto);
    }

    @PostMapping("commentUpdate")
    public int commentUpdate(CommentDto commentDto){
        return commentService.commentUpdate(commentDto);
    }

    @PostMapping("commentList")
    public List<CommentDto> commentList(CommentDto commentDto){
        return commentService.commentList(commentDto);
    }

    @PostMapping("replaySave")
    public int replaySave(ReplayDto replayDto){
        return commentService.replaySave(replayDto);
    }

    @PostMapping("replayUpdate")
    public int replayUpdate(ReplayDto replayDto){
        return commentService.replayUpdate(replayDto);
    }

    @PostMapping("replayList")
    public List<ReplayDto> replayList(ReplayDto replayDto){
        return commentService.replayList(replayDto);
    }
}
