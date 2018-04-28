package za.co.admire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import za.co.admire.service.ProcessTextFile;

/**
 * Created by admirechifura on 27/04/2018.
 */
@SpringBootApplication
public class TextToJavaApplication {
    public static void main(final String[] args) {
        SpringApplication.run(TextToJavaApplication.class, args);

    }
}
