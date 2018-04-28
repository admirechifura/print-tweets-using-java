package za.co.admire.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admirechifura on 27/04/2018.
 */
@ConfigurationProperties("camel.properties")
@Configuration
public class CamelProperties {

    private String readLockMode = "changed";
    private int readLockCheckInterval = 2000;
    private String incomingFilePath = "/tmp/textToJavaFiles";

    public String getReadLockMode() {
        return readLockMode;
    }

    public void setReadLockMode(final String readLockMode) {
        this.readLockMode = readLockMode;
    }

    public int getReadLockCheckInterval() {
        return readLockCheckInterval;
    }

    public void setReadLockCheckInterval(final int readLockCheckInterval) {
        this.readLockCheckInterval = readLockCheckInterval;
    }

    public String getIncomingFilePath() {
        return incomingFilePath;
    }

    public void setIncomingFilePath(final String incomingFilePath) {
        this.incomingFilePath = incomingFilePath;
    }
}
