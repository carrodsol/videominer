package com.aiss.dailymotionminer.service;

import com.aiss.dailymotionminer.model.dailymotion.InfoPaginacionVideo;
import com.aiss.dailymotionminer.model.dailymotion.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${DailyMotionMiner.baseUri}")
    private String baseUri;

    // Campos solicitados, todos definidos en application.propeties:
    // id,title,description,created_time,channel,tags,owner
    @Value("${DailyMotionMiner.videoFields}")
    private String fields;

    // Encuentra simplemente los videos teniendo en cuenta la paginación.
    public List<Video> findAllVideos(Integer maxVideos, Integer maxPages) {
        List<Video> videos = new ArrayList<>();
        int page = 1;
        boolean hasMore = true;
        while (hasMore && (page <= maxPages) && (videos.size() < maxVideos)) {
            int limit = Math.min(maxVideos - videos.size(), maxVideos);
            String url = this.baseUri + "/videos" + fields + "&limit=" + limit + "&page=" + page;
                InfoPaginacionVideo listaVideos = restTemplate.getForObject(url, InfoPaginacionVideo.class);
            assert listaVideos != null;
            videos.addAll(listaVideos.getVideos());
                hasMore = listaVideos.getHasMore();
                page++;
        }
        return videos;
    }

    // Encuentra los videos de un canal teniendo en cuenta la paginación
    public List<Video> findAllVideosByChannelId(String channelId, Integer maxVideos, Integer maxPages) {
        List<Video> videos = new ArrayList<>();
        int page = 1;
        boolean hasMore = true;
        while (hasMore && (page <= maxPages) && (videos.size() < maxVideos)) {
            int limit = Math.min(maxVideos - videos.size(), maxVideos);
            String url = this.baseUri + "/videos" + fields + "&limit=" + limit + "&page=" + page + "&channel=" + channelId;
                InfoPaginacionVideo listaVideos = restTemplate.getForObject(url, InfoPaginacionVideo.class);
            assert listaVideos != null;
            videos.addAll(listaVideos.getVideos());
                hasMore = listaVideos.getHasMore();
                page++;
        }
        return videos;
    }

    // Encuentra video por ID
    public Video findVideoById(String videoId) {
        return restTemplate.getForObject(this.baseUri + "/video/" + videoId + "/" + fields, Video.class);
    }


}
