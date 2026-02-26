package com.example.springlesson.bowling;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class BowlingConfig {

    @Value("${server.port:8080}")
    private String port;

    @Bean
    CommandLineRunner commandLineRunner(
            BowlingRepository repository) {
        return args -> {
            Bowling user = new Bowling(
                    "Aiden Lehman",
                    "Aiden.Lehman627@gmail.com",
                    215
            );
            repository.save(user);
            // Wait a moment for Tomcat to fully start before opening the browser
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    openHomePage();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        };
    }

    private void openHomePage() {
        String url = "http://localhost:" + port + "/api/v1/bowling";
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();

        try {
            if (os.indexOf("win") >= 0) {
                rt.exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", url});
            } else if (os.indexOf("mac") >= 0) {
                rt.exec(new String[]{"open", url});
            } else if (os.indexOf("nix") >=0 || os.indexOf("nux") >=0) {
                rt.exec(new String[]{"xdg-open", url});
            }
        } catch (IOException e) {
            // Silently fail if browser can't be opened
        }
    }
}
