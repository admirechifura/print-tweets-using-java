package za.co.admire.service.integrationtests;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.admire.configuration.CamelProperties;
import za.co.admire.service.ProcessTextFileUsingSpring;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admirechifura on 27/04/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProcessTextFileUsingSpringIntegrationTest {

    @Autowired
    private ProcessTextFileUsingSpring processTextFile;

    @Autowired
    private CamelProperties camelProperties;

    private Map<String, Object> camelHeaders = new HashMap();

    @Before
    public void setup() {
        ClassLoader classLoader = getClass().getClassLoader();
        File userFile = new File(classLoader.getResource(camelProperties.getIncomingFilePath() + "/user.txt").getFile());
        File tweetFile = new File(classLoader.getResource(camelProperties.getIncomingFilePath() + "/tweet.txt").getFile());
        camelHeaders.put("UserFileAbsolutePath", userFile.getAbsolutePath());
        camelHeaders.put("TweetFileAbsolutePath", tweetFile.getAbsolutePath());
    }

    @Test
    public void when_processTextFileIsCalledWithTwoFilesWithValidData_expect_usersAndTweetsToBePrintedSuccessfully() {
        String routeId = "incoming-text-file-route";
        CamelContext camelContext = new DefaultCamelContext();
        Exchange defaultExchange = new DefaultExchange(camelContext);
        defaultExchange.setFromRouteId(routeId);
        org.apache.camel.Message message = defaultExchange.getIn();
        message.setHeaders(camelHeaders);
        defaultExchange.setIn(message);
        processTextFile.process(defaultExchange);

    }

}
