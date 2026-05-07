package com.aiss.dailymotionminer;
import com.aiss.dailymotionminer.controller.ChannelController;
import com.aiss.dailymotionminer.controller.VideoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataRunner implements CommandLineRunner {

    @Autowired
    ChannelController c;
    @Autowired
    VideoController v;

    public InitialDataRunner(ChannelController c, VideoController v) {
        this.c = c;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Arrancando proceso de carga inicial automática...");

        try {
            Thread.sleep(15000);
            System.out.println("¡Lanzando la petición a PeerTube y enviando a VideoMiner!");
            c.postChannelToVideoMiner("drama",10,2);
            c.postChannelToVideoMiner("shortfilms",10,2);

            System.out.println("¡Carga inicial completada con éxito!");

        } catch (Exception e) {
            System.out.println("Error en la carga inicial: " + e.getMessage());
        }
    }
}