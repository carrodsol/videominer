package com.aiss.peertubeminer.etl;

import com.aiss.peertubeminer.model.peertube.PTCaptionDatum;
import com.aiss.peertubeminer.model.peertube.PTCommentDatum;
import com.aiss.peertubeminer.model.videominer.VMComment;
import com.aiss.peertubeminer.service.PeerTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentETL {

    private final PeerTubeService commentService;

    @Autowired
    public CommentETL(PeerTubeService commentService) {
        this.commentService = commentService;
    }

    public List<VMComment> transform(String videoId, Integer maxComments) {
        List<PTCommentDatum> comments = commentService.getComments(videoId, maxComments).getData();
        List<VMComment> vmComments = new ArrayList<>();
        comments.forEach(ptComment -> {
            VMComment comment = new VMComment();
                comment.setId(ptComment.getId().toString());
                comment.setText(ptComment.getText());
                comment.setCreatedOn(ptComment.getCreatedOn());
                vmComments.add(comment);
        });
        return vmComments;
    }


}
