package com.aiss.dailymotionminer;
import com.aiss.dailymotionminer.controller.ChannelController;
import com.aiss.dailymotionminer.controller.VideoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataRunner implements CommandLineRunner {

    @Autowired
    ChannelController c;
    @Autowired
    VideoController v;

    @Value("${videominer.api.key}")
    private String apiKey;

    public InitialDataRunner(ChannelController c, VideoController v) {
        this.c = c;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Arrancando proceso de carga inicial automática...");

        try {
            Thread.sleep(15000);
            System.out.println("¡Lanzando la petición a DailyMotion y enviando a VideoMiner!");
            c.postChannelToVideoMiner("music",10,2, apiKey);
            c.postChannelToVideoMiner("shortfilms",10,2, apiKey);

            System.out.println("¡Carga inicial completada con éxito!");

        } catch (Exception e) {
            System.out.println("Error en la carga inicial: " + e.getMessage());
        }
    }
}