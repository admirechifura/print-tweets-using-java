package za.co.admire.service;

import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by admirechifura on 27/04/2018.
 */
@Service
public class ProcessTextFileUsingSpring {

    public static final Logger log = LoggerFactory.getLogger(ProcessTextFileUsingSpring.class);

    private FileParserService fileParserService;
    private PrintAndFormatService printAndFormatService;

    @Autowired
    public ProcessTextFileUsingSpring(final FileParserService fileParserService, final PrintAndFormatService printAndFormatService) {
        this.fileParserService = fileParserService;
        this.printAndFormatService = printAndFormatService;
    }

    public void process(final Exchange exchange) {
        String userFileAbsolutePath = (String) exchange.getIn().getHeader("UserFileAbsolutePath");
        String userFilename = userFileAbsolutePath.substring(userFileAbsolutePath.lastIndexOf('/') + 1);

        String tweetFileAbsolutePath = (String) exchange.getIn().getHeader("TweetFileAbsolutePath");
        String tweetFilename = tweetFileAbsolutePath.substring(userFileAbsolutePath.lastIndexOf('/') + 1);
        log.info("Received user input file:{}", userFilename);
        log.info("Received tweet input file:{}", tweetFilename);
        Map<String, HashSet<String>> followersAndUsers = fileParserService.parseUserFile(userFileAbsolutePath);
        Map<Double, LinkedList<String>> userAndTweets = fileParserService.parseTweetFile(tweetFileAbsolutePath);
        printAndFormatService.printToConsole(followersAndUsers, userAndTweets);
        log.info("<<<<<<<<<<<<<<< Program Ended Of Output Solution >>>>>>>>>>>>>>");

    }

}
