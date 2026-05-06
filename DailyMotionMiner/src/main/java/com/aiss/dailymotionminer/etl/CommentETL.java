package com.aiss.dailymotionminer.etl;

import com.aiss.dailymotionminer.model.dailymotion.Video;
import com.aiss.dailymotionminer.model.videominer.VMComment;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommentETL {
    
    public List<VMComment> transform(Video video) {
        List<String> comments = video.getTags();
        List<VMComment> vmComments = new ArrayList<>();
        // A cada video se le asocia un comentario con un id autogenerado empezando por 0
        if (comments != null) {
            for(int i=0; i<comments.size(); i++) {
                vmComments.add(
                        new VMComment(
                                video.getId()+"_"+i,
                                // Cada comentario tiene como fecha de creación la del vídeo
                                video.getCreatedTime().toString(),
                                comments.get(i)
                        )
                );
            }
        }
        return vmComments;
    }
}