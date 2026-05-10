package com.aiss.peertubeminer;
import com.aiss.peertubeminer.controller.ChannelController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataRunner implements CommandLineRunner {

    @Autowired
    ChannelController c;

    @Value("${videominer.api.key}")
    private String apiKey;

    public InitialDataRunner(ChannelController c) {
        this.c = c;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Arrancando proceso de carga inicial automática...");

        try {
            Thread.sleep(15000);
            System.out.println("¡Lanzando la petición a PeerTube y enviando a VideoMiner!");
            c.postChannel("tv",10,2, apiKey);
            c.postChannel("chem0013_videos",10,2, apiKey);
            System.out.println("¡Carga inicial completada con éxito!");

        } catch (Exception e) {
            System.out.println("Error en la carga inicial: " + e.getMessage());
        }
    }
}