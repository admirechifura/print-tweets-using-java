package za.co.admire.routes;

import static java.lang.String.format;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import za.co.admire.configuration.CamelProperties;
import za.co.admire.service.ProcessTextFile;

/**
 * Created by admirechifura on 27/04/2018.
 */
@Component
public class ProcessIncomingFileRoute extends RouteBuilder {

    private CamelProperties camelProperties;

    public ProcessIncomingFileRoute(final CamelProperties camelProperties) {
        this.camelProperties = camelProperties;
    }

    @Override
    public void configure() throws Exception {
        // @formatter:off
        from(format("file://%s?readLock=%s&readLockCheckInterval=%s&maxMessagesPerPoll=2", camelProperties.getIncomingFilePath(), camelProperties.getReadLockMode(),
                camelProperties.getReadLockCheckInterval()))
                .autoStartup(Boolean.TRUE.toString())
                .to(format("file://%s/.processed", camelProperties.getIncomingFilePath()))
                .bean(ProcessTextFile.class)
                .routeId("incoming-text-file-route")
                .description(format("%s:%s", "text-file-from-folder", camelProperties.getIncomingFilePath()));
        // @formatter:on
    }
}
