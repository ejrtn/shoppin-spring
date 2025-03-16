package com.example.shopping.comment;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    public boolean commentSave(CommentDto commentDto){
        return true;
    }

    public boolean commentUpdate(CommentDto commentDto){
        return true;
    }

    public List<Map<String,String>> commentList(CommentDto commentDto){
        return new ArrayList<>();
    }

    public boolean replaySave(ReplayDto replayDto){
        return true;
    }

    public boolean replayUpdate(ReplayDto replayDto){
        return true;
    }

    public List<Map<String,String>> replayList(ReplayDto replayDto){
        return new ArrayList<>();
    }

}
