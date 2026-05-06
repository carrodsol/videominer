package com.aiss.peertubeminer.etl;

import com.aiss.peertubeminer.model.peertube.PTCommentDatum;
import com.aiss.peertubeminer.model.videominer.VMComment;
import com.aiss.peertubeminer.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class CommentETL {

    private final CommentService commentService;

    @Autowired
    public CommentETL(CommentService commentService) {
        this.commentService = commentService;
    }

    @Async("etlExecutor")
    public CompletableFuture<List<VMComment>> transform(String videoId, Integer maxComments) {
        List<PTCommentDatum> comments = commentService.getComments(videoId, maxComments).getData();
        List<VMComment> vmComments = new ArrayList<>();
        if (comments == null || comments.isEmpty()) {
            return CompletableFuture.completedFuture(vmComments);
        }

        comments.forEach(ptComment -> {
            if (ptComment == null) {
                return;
            }
            VMComment comment = new VMComment();
            String commentId = ptComment.getUuid() != null ? ptComment.getUuid() :
                    (ptComment.getId() != null ? ptComment.getId().toString() : null);
            comment.setId(commentId);
            comment.setText(ptComment.getText());
            comment.setCreatedOn(ptComment.getCreatedOn());
            vmComments.add(comment);
        });
        return CompletableFuture.completedFuture(vmComments);
    }


}
