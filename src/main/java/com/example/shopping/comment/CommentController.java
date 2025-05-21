package com.example.shopping.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("commentSave")
    @ResponseBody
    public int commentSave(CommentDto commentDto){
        return commentService.commentSave(commentDto);
    }

    @PostMapping("commentUpdate")
    @ResponseBody
    public int commentUpdate(CommentDto commentDto){
        return commentService.commentUpdate(commentDto);
    }

    @PostMapping("productReview")
    @ResponseBody
    public List<CommentDto> productReview(@RequestParam String productId,@RequestParam int reviewStartNum){
        return commentService.productReview(productId,reviewStartNum);
    }


    @PostMapping("replaySave")
    public int replaySave(ReplayDto replayDto){
        return commentService.replaySave(replayDto);
    }

    @PostMapping("replayUpdate")
    public int replayUpdate(ReplayDto replayDto){
        return commentService.replayUpdate(replayDto);
    }

}
